library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity practica7 is
	Port( M : in STD_LOGIC;
			FC1 : in STD_LOGIC;
			FC2 : in STD_LOGIC;
			FC3 : in STD_LOGIC;
			FC4 : in STD_LOGIC;
			CK : in STD_LOGIC;
			RS : in STD_LOGIC;
			T : out STD_LOGIC;
			EP1 : out STD_LOGIC;
			EP2 : out STD_LOGIC;
			RP1 : out STD_LOGIC;
			RP2 : out STD_LOGIC);
end practica7;

architecture Moore of practica7 is
	type estados is (espera, A, B, C, D, E);
	signal estado : estados;
	
begin
	EVOLUCION : process
		begin
		wait until CK = '1';
		if (RS = '1') 
			then estado <= espera;
		else
			case estado is
				when espera => if (M = '1') then estado <= A; end if;
				when A => if (M = '0') then estado <= B; end if;
				when B => if (FC4 = '1') then estado <= C; end if;
				when C => if (FC2 = '1') then estado <= D; end if;
				when D => if (FC1 = '1') then estado <= E; end if;
				when E => if (FC3 = '1') then estado <= espera; end if;
				when others =>
			end case;
		end if;
	end process;
	
	-- Activacion de las salidas
	SALIDAS: process (estado)
		begin
			T <= '0'; EP1 <= '0'; RP1 <= '0'; EP2 <= '0'; RP2 <= '0';
			case estado is
				when espera =>
				when A =>
				when B => EP2 <= '1';
				when C => T <= '1'; EP1 <= '1';
				when D => T <= '1'; RP1 <= '1';
				when E => RP2 <= '1';
				when others =>
			end case;
	end process;
		
end Moore;