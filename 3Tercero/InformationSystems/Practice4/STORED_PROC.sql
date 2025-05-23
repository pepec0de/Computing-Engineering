DECLARE
    m_num "MEMBER".M_NUM%TYPE;
    m_name "MEMBER".M_NAME%TYPE;
    m_email "MEMBER".M_EMAILMEMBER%TYPE;
    id ACTIVITY.A_ID%TYPE := 'AC01';
    cur SYS_REFCURSOR;
BEGIN
    DBMS_OUTPUT.PUT_LINE('MEMBERS IN ACTIVITY ' || id);
    DBMS_OUTPUT.PUT_LINE('NUMBER    NAME	EMAIL');
    DBMS_OUTPUT.PUT_LINE('-----    -------	-------');
    findactivitymembers(id, cur);
    LOOP
        FETCH cur INTO m_num, m_name, m_email;
        EXIT WHEN cur%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(m_num || '     ' || m_name || '     ' || m_email);
    END LOOP;
    CLOSE cur;
END;

CREATE OR REPLACE PROCEDURE ISDD_003.FINDACTIVITYMEMBERS(ACTID IN ACTIVITY.A_ID%TYPE, C_MEMBERS IN OUT SYS_REFCURSOR) IS
BEGIN 
	OPEN C_MEMBERS FOR SELECT m.M_NUM, m.M_NAME, m.M_EMAILMEMBER FROM (PERFORMS p INNER JOIN "MEMBER" m ON p.P_NUM = m.M_NUM) WHERE p.P_ID = ACTID;
END;
