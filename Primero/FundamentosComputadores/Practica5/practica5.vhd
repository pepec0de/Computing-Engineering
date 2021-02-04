----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:24:00 12/18/2020 
-- Design Name: 
-- Module Name:    practica5 - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
-- Practica 5
-- Pepe González
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity practica5 is
    Port ( G3 : in  STD_LOGIC;
           G2 : in  STD_LOGIC;
           G1 : in  STD_LOGIC;
           G0 : in  STD_LOGIC;
           P : out  STD_LOGIC;
           S : out  STD_LOGIC;
           T : out  STD_LOGIC;
           C : out  STD_LOGIC;
			  S0 : out STD_LOGIC;
			  S1 : out STD_LOGIC;
			  S2 : out STD_LOGIC;
			  S3 : out STD_LOGIC);
end practica5;

architecture Behavioral of practica5 is
	signal entrada: std_logic_vector(3 downto 0);

begin
	entrada <= G3 & G2 & G1 & G0;
	P <=	'1' when entrada = "0000" else
			'1' when entrada = "0001" else
			'1' when entrada = "0011" else
			'1' when entrada = "0010" else
			'0';
			
	with entrada select
		S <=	'1' when "0110",
				'1' when "0111",
				'1' when "0101",
				'1' when "0100",
				'0' when others;
	process (entrada)
	begin
	if entrada = "1100" then T <= '1'; elsif
		entrada = "1101" then T <= '1'; elsif
		entrada = "1111" then T <= '1'; elsif
		entrada = "1110" then T <= '1'; else
	T <= '0';
	end if;
	
	case entrada is
		when "1010" => C <= '1';
		when "1011" => C <= '1';
		when "1001" => C <= '1';
		when "1000" => C <= '1';
		when others => C <= '0';
	end case;
	end process;
	
	S0 <=((not G0) and G1 and (not G2) and (not G3))
    or(G0 and (not G1) and (not G2) and (not G3)) 
    or (G0 and G1 and (not G2) and G3 ) 
    or ((not G0)and (not G1) and (not G2) and G3 ) 
    or ((not G0)and G1 and G2 and G3)
    or (G0 and (not G1) and G2 and G3)
    or ((not G0)and (not G1) and G2 and (not G3) )
    or (G0 and G1 and G2 and (not G3));

	S1 <= ((not G2)and (not G3) and G1) or ((not G3)and (not G1) and G2)
            or (G3 and G2 and G1) or (G3 and (not G2) and (not G1));
    S2 <= ((not G2)and G3) or (G2 and (not G3));
    S3 <= G3;
	
end Behavioral;