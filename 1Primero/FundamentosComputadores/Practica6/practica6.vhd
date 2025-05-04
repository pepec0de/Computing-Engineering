library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_SIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity practica6 is
	Port( S : in STD_LOGIC_VECTOR(1 DOWNTO 0);
			M : in STD_LOGIC;
			A : in STD_LOGIC_VECTOR(3 DOWNTO 0);
			B : in STD_LOGIC_VECTOR(3 DOWNTO 0);
			CI: in STD_LOGIC;
			F : out STD_LOGIC_VECTOR(3 DOWNTO 0);
			CO: out STD_LOGIC);
end practica6;

architecture Behavioral of practica6 is
begin
	F <=	(A NOR B)		when M = '0' and S = "00" else
			(A AND B)		when M = '0' and S = "01" else
			(A XNOR B)		when M = '0' and S = "10" else
			(NOT B)			when M = '0' and S = "11" else
			((A - B) - CI)	when M = '1' and S = "00" else
			(A + 1)			when M = '1' and S = "01" else
			((A + B) + CI)	when M = '1' and S = "10" else
			((A XOR B) - 1);
	CO <= '0';
end Behavioral;

