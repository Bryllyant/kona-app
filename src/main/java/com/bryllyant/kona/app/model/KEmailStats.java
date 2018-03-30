package com.bryllyant.kona.app.model;

import com.bryllyant.kona.data.model.KBaseModel;

public class KEmailStats extends KBaseModel  {
	private static final long serialVersionUID = 1L;
    
	Double failed = 0.0;
	Double failedRate = 0.0;
	Double delivered = 0.0;
	Double deliveredRate = 0.0;
	Double bounced = 0.0;
	Double bouncedRate = 0.0;
	Double complained = 0.0;
	Double complainedRate = 0.0;
	Double optedOut = 0.0;
	Double optedOutRate = 0.0;
	Double opened = 0.0;
	Double openedAllRate = 0.0;
	Double clicked = 0.0;
	Double clickedAllRate = 0.0;
	Double printed = 0.0;
	Double printedAllRate = 0.0;
	Double forwarded = 0.0;
	Double forwardedAllRate = 0.0;

	Double openedDeliveredRate = 0.0;
	Double clickedDeliveredRate = 0.0;
	Double printedDeliveredRate = 0.0;
	Double forwardedDeliveredRate = 0.0;

	Double clickedOpenedRate = 0.0;
	Double printedOpenedRate = 0.0;
	Double forwardedOpenedRate = 0.0;

	Double emailCount = 0.0;

	public KEmailStats() {
	}


    public Double getFailed() {
        return failed;
    }

    public void setFailed(Double failed) {
        this.failed = failed;
    }

    public Double getFailedRate() {
        return failedRate;
    }

    public void setFailedRate(Double failedRate) {
        this.failedRate = failedRate;
    }

    public Double getDelivered() {
        return delivered;
    }

    public void setDelivered(Double delivered) {
        this.delivered = delivered;
    }

    public Double getDeliveredRate() {
        return deliveredRate;
    }

    public void setDeliveredRate(Double deliveredRate) {
        this.deliveredRate = deliveredRate;
    }

    public Double getBounced() {
        return bounced;
    }

    public void setBounced(Double bounced) {
        this.bounced = bounced;
    }

    public Double getBouncedRate() {
        return bouncedRate;
    }

    public void setBouncedRate(Double bouncedRate) {
        this.bouncedRate = bouncedRate;
    }

    public Double getComplained() {
        return complained;
    }

    public void setComplained(Double complained) {
        this.complained = complained;
    }

    public Double getComplainedRate() {
        return complainedRate;
    }

    public void setComplainedRate(Double complainedRate) {
        this.complainedRate = complainedRate;
    }

    public Double getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Double optedOut) {
        this.optedOut = optedOut;
    }

    public Double getOptedOutRate() {
        return optedOutRate;
    }

    public void setOptedOutRate(Double optedOutRate) {
        this.optedOutRate = optedOutRate;
    }

    public Double getOpened() {
        return opened;
    }

    public void setOpened(Double opened) {
        this.opened = opened;
    }

    public Double getOpenedAllRate() {
        return openedAllRate;
    }

    public void setOpenedAllRate(Double openedAllRate) {
        this.openedAllRate = openedAllRate;
    }

    public Double getClicked() {
        return clicked;
    }

    public void setClicked(Double clicked) {
        this.clicked = clicked;
    }

    public Double getClickedAllRate() {
        return clickedAllRate;
    }

    public void setClickedAllRate(Double clickedAllRate) {
        this.clickedAllRate = clickedAllRate;
    }

    public Double getPrinted() {
        return printed;
    }

    public void setPrinted(Double printed) {
        this.printed = printed;
    }

    public Double getPrintedAllRate() {
        return printedAllRate;
    }

    public void setPrintedAllRate(Double printedAllRate) {
        this.printedAllRate = printedAllRate;
    }

    public Double getForwarded() {
        return forwarded;
    }

    public void setForwarded(Double forwarded) {
        this.forwarded = forwarded;
    }

    public Double getForwardedAllRate() {
        return forwardedAllRate;
    }

    public void setForwardedAllRate(Double forwardedAllRate) {
        this.forwardedAllRate = forwardedAllRate;
    }

    public Double getOpenedDeliveredRate() {
        return openedDeliveredRate;
    }

    public void setOpenedDeliveredRate(Double openedDeliveredRate) {
        this.openedDeliveredRate = openedDeliveredRate;
    }

    public Double getClickedDeliveredRate() {
        return clickedDeliveredRate;
    }

    public void setClickedDeliveredRate(Double clickedDeliveredRate) {
        this.clickedDeliveredRate = clickedDeliveredRate;
    }

    public Double getPrintedDeliveredRate() {
        return printedDeliveredRate;
    }

    public void setPrintedDeliveredRate(Double printedDeliveredRate) {
        this.printedDeliveredRate = printedDeliveredRate;
    }

    public Double getForwardedDeliveredRate() {
        return forwardedDeliveredRate;
    }

    public void setForwardedDeliveredRate(Double forwardedDeliveredRate) {
        this.forwardedDeliveredRate = forwardedDeliveredRate;
    }

    public Double getClickedOpenedRate() {
        return clickedOpenedRate;
    }

    public void setClickedOpenedRate(Double clickedOpenedRate) {
        this.clickedOpenedRate = clickedOpenedRate;
    }

    public Double getPrintedOpenedRate() {
        return printedOpenedRate;
    }

    public void setPrintedOpenedRate(Double printedOpenedRate) {
        this.printedOpenedRate = printedOpenedRate;
    }

    public Double getForwardedOpenedRate() {
        return forwardedOpenedRate;
    }

    public void setForwardedOpenedRate(Double forwardedOpenedRate) {
        this.forwardedOpenedRate = forwardedOpenedRate;
    }

    public Double getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(Double emailCount) {
        this.emailCount = emailCount;
    }
}
