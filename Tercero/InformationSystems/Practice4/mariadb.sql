DELIMITER //

CREATE PROCEDURE FINDACTIVITYMEMBERS(
  IN ACTID CHAR(4)
)
 BEGIN
  select m.M_NUM, m.M_NAME, m.M_EMAILMEMBER from PERFORMS p inner join `MEMBER` m on p.P_NUM = m.M_NUM WHERE p.P_ID = ACTID;
 END;
//

DELIMITER ;
