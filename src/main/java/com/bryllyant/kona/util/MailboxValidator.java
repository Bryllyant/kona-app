package com.bryllyant.kona.util;

import com.bryllyant.kona.locale.KValidator;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.CommunicationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


// Basically taken and modified from rgagnon: 
// See: http://www.rgagnon.com/javadetails/java-0452.html
// License: 
//    http://www.rgagnon.com/varia/faq-e.html#license
//    There is no restriction to use individual How-To in a development (compiled/source) but a mention is appreciated.

public class MailboxValidator {
	private static Logger logger = LoggerFactory.getLogger(MailboxValidator.class);

	//private String fromDomain;
	//private String fromEmail;

    private static Cache<String, Optional<List<String>>> mxCache = Caffeine.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .maximumSize(100_000)
            .build();

//	public MailboxValidator(String fromEmail) {
//		this.fromEmail = fromEmail;
//		this.fromDomain = fromEmail.substring(fromEmail.indexOf("@")+1);
//	}
//
//	public static void main(String args[]) {
//		String testData[] = {
//				"blisdcasdcasdc@googlemail.com",
//				"info@bilderbuch-stoff.de",
//				"xxx@bilderbuch-stoff.de", // invalid
//				"s.muncke@tarent.de", //invalid
//				"fail.me@nowhere.spam" // Invalid domain name
//		};
//
//		MailboxValidator validator = new MailboxValidator(args[0]);
//
//		for (int ctr = 0; ctr < testData.length; ctr++) {
//			System.out.println(testData[ctr] + " is valid? "
//					+ validator.mayMailboxExist(testData[ctr], false));
//		}
//
//		return;
//	}

	public static boolean isEmailSyntaxValid(String email) {
		return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public static boolean doesHostExist(String email) {
		String host = email.substring(email.indexOf("@")+1);

		try {
			Inet4Address.getByName(host);
		} catch (UnknownHostException e) {
			logger.info("[mail validation] host of mail does not exist email=" + email + " - " + e.getMessage());
			return false;
		}

		return true;
	}   

	private static List<String> getMX(String hostName) throws NamingException {
	    Optional<List<String>> result = mxCache.getIfPresent(hostName);

	    try {

            // we don't have a value yet for this key
            if (result == null) {
                // Perform a DNS lookup for MX records in the domain
                Hashtable<String, String> env = new Hashtable<>();

                env.put("java.naming.factory.initial",
                        "com.sun.jndi.dns.DnsContextFactory");

                DirContext ictx = new InitialDirContext(env);
                Attributes attrs = ictx.getAttributes(hostName, new String[]{"MX"});
                Attribute attr = attrs.get("MX");

                // if we don't have an MX record, try the machine itself
                if ((attr == null) || (attr.size() == 0)) {
                    attrs = ictx.getAttributes(hostName, new String[]{"A"});

                    attr = attrs.get("A");

                    if (attr == null) {
                        // add empty value to know we've processed this domain
                        mxCache.put(hostName, Optional.empty());
                        throw new NamingException("No match for name '" + hostName + "'");
                    }
                }

                // Huzzah! we have machines to try. Return them as an array list
                // NOTE: We SHOULD take the preference into account to be absolutely
                // correct. This is left as an exercise for anyone who cares.
                List<String> records = new ArrayList<>();

                NamingEnumeration<?> en = attr.getAll();

                while (en.hasMore()) {
                    String mailhost;
                    String x = (String) en.next();
                    String f[] = x.split(" ");
                    // THE fix *************
                    if (f.length == 1)
                        mailhost = f[0];
                    else if (f[1].endsWith("."))
                        mailhost = f[1].substring(0, (f[1].length() - 1));
                    else
                        mailhost = f[1];
                    // THE fix *************
                    records.add(mailhost);
                }

                logger.debug("MX hosts:\n" + KStringUtil.join(records, "\n"));

                // cannot set null value using Optional.of().  user Optional.ofNullable() if the value can be null.
                result = Optional.of(records);

                mxCache.put(hostName, result);
            }
        } catch (Throwable t) {
	        logger.debug(t.getMessage());
	        result = Optional.empty();
            mxCache.put(hostName, result);
        }

        if (result.isPresent()) {
            return result.get();
        }

        throw new NamingException("No match for name '" + hostName + "'");
	}

	public static boolean mailboxExists(String address, boolean tryConnectMX) {
		if (!KValidator.isEmail(address) || !isValidEmailAddress(address)) {
			return false;
		}

		// Find the separator for the domain name
		int pos = address.indexOf('@');

		// If the address does not contain an '@', it's not valid
		if ( pos == -1 ) return false;

		// Isolate the domain/machine name and get a list of mail exchangers
		String domain = address.substring( ++pos );

		List<String> mxList = null;

		try {
			mxList = getMX(domain);
		}
		catch (CommunicationException ce) {
			logger.info("[mail validation] DNS Exception.  Rejecting email: " + address, ce);
			return false;
		} catch (NamingException ex) {
			logger.info("[mail validation] Host Naming Exception. Rejecting email: " + address, ex);
			return false;
		}

		// if we do not find an mx, we believe the address anyway
		// if ( mxList.size() == 0 ) return true;
        
		if (mxList.size() == 0) {
		    return false;
        }

        if (tryConnectMX) {
		    boolean connected = connectMX(address, mxList);
		    logger.debug("Connection to address [" + address + "]: " + (connected ? "OK" : "ERROR"));
		    return connected;
        }

        return true;
	}
    
	public static boolean connectMX(String address, List<String> mxList) {
		try {
            // modification, SMa: mx only use the first mx
            int mx = 0;
			String response;
			//
			String mxHost = mxList.get(mx);

			logger.debug("[connectMX] Connecting to " + mxHost + " ...");

			Socket skt = new Socket(mxHost, 25);

			BufferedReader rdr = new BufferedReader
					( new InputStreamReader( skt.getInputStream() ) );

			BufferedWriter wtr = new BufferedWriter
					( new OutputStreamWriter( skt.getOutputStream() ) );

			response = hear(rdr);

			if (getResponseCode(response) != 220) {
				skt.close();
				throw new Exception( "Invalid header" );
			}

			// say(wtr, "EHLO "+ this.fromDomain);

            String fromDomain = address.substring(address.indexOf("@")+1);
            say(wtr, "EHLO "+ fromDomain);

			response = hear(rdr);

			if (getResponseCode(response) != 250) {
				skt.close();
				throw new Exception( "Not ESMTP" );
			}

			// validate the sender address              
			// say( wtr, "MAIL FROM: <"+this.fromEmail+">" );
            say( wtr, "MAIL FROM: <"+ address +">" );

			response = hear(rdr);

			if (getResponseCode(response) != 250) {
				skt.close();
				throw new Exception("Sender rejected");
			}

			say(wtr, "RCPT TO: <" + address + ">");

			response = hear(rdr);

			// be polite
			say( wtr, "RSET" ); hear( rdr );
			say( wtr, "QUIT" ); hear( rdr );

			rdr.close();
			wtr.close();
			skt.close();

			if (getResponseCode(response) == 550) {
			    String resp = response.trim().toLowerCase();

			    if (resp.contains("address rejected")
                        || resp.contains("user unknown")
                        || resp.contains("email account that you tried to reach does not exist")) {
                    logger.info("[connectMX] SMTP 550:  Invalid email address: " + address);
                } else if (resp.contains("access denied")) {
                    // 550 - spam filter blocked email?
                    logger.info("[connectMX] SMTP 550: Access Denied: email: " + address + "\n" + response);
                } else {
                    logger.info("[connectMX] SMTP 550: email: " + address + "\n" + response);
                }

				return false;
			}

            return true;
		} catch (Exception e) {
			logger.info("[connectMX] Remote mail validation error.  Rejecting email: " + address + " - " + e.getMessage());
			return false;
		}

	}

	private static int getResponseCode(String response) {
        String prefix = response.substring(0, 3);

        int code = 0;

        try {
            code = Integer.parseInt(prefix);
        } catch (Exception ex) {
            code = -1;
        }

        return code;
    }

	private static String hear(BufferedReader in) throws IOException {
		String line = null;
		int res = 0;

		while ((line = in.readLine()) != null) {
		    logger.debug("[hear] " + line);

			String pfx = line.substring(0, 3);

			try {
				res = Integer.parseInt(pfx);
			} catch (Exception ex) {
				res = -1;
			}

			if (line.charAt(3) != '-')
				break;
		}

		return line;
	}

	private static void say(BufferedWriter wr, String text) throws IOException {
        logger.debug("[say] " + text);
		wr.write(text + "\r\n");
		wr.flush();
	}

}
