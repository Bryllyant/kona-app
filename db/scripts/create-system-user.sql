/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

set @userId = 1;
set @accountId = 1;
set @accountSlug = 'system';
set @accountName = 'System';


-- ----------------------- 
-- Create account record
-- ----------------------- 
insert into kona__account(
    id,
    uid,
    owner_id,
    slug,
    name,
    enabled,
    verified,
    created_date
) values (
    @accountId,
    replace(uuid(),'-',''),
    @userId,
    @accountSlug,
    @accountName,
    1,
    1,
    now()
);


-- ----------------------- 
-- Create System user
-- This is strictly an internal account that's used to run
-- background jobs, etc. and has no user_auth record.
-- ----------------------- 
set @username = 'system';
set @firstName = 'System';
set @lastName = 'User';
set @displayName = 'System';

set @type = 'SYSTEM'; -- SYSTEM Type
set @roleId = 100; -- SYSTEM Role


insert into kona__user(
    id, 
    uid, 
    type,
    account_id,
    username,
    first_name, 
    last_name, 
    display_name,
    enabled,
    created_date
) values (
    @userId, 
    replace(uuid(),'-',''),
    @type,
    @accountId,
    @username,
    @firstName, 
    @lastName, 
    @displayName, 
    1, 
    now()
);

insert into kona__user_role(
    uid,
    user_id,
    role_id
) values (
    replace(uuid(),'-',''),
    @userId,
    @roleId
);



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

