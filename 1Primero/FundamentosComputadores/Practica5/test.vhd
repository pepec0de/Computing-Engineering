--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:27:35 12/19/2020
-- Design Name:   
-- Module Name:   /home/pepe/Documentos/Universidad/UWU/Asignaturas/Fundamentos de computadores/Xilinx ISE Projects/Practica5/test.vhd
-- Project Name:  Practica5
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: practica5
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY test IS
END test;
 
ARCHITECTURE behavior OF test IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT practica5
    PORT(
         G3 : IN  std_logic;
         G2 : IN  std_logic;
         G1 : IN  std_logic;
         G0 : IN  std_logic;
         P : OUT  std_logic;
         S : OUT  std_logic;
         T : OUT  std_logic;
         C : OUT  std_logic;
			S0 : OUT  std_logic;
			S1 : OUT  std_logic;
			S2 : OUT  std_logic;
			S3 : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal G3 : std_logic := '0';
   signal G2 : std_logic := '0';
   signal G1 : std_logic := '0';
   signal G0 : std_logic := '0';

 	--Outputs
   signal P : std_logic;
   signal S : std_logic;
   signal T : std_logic;
   signal C : std_logic;
	signal S0 : std_logic;
	signal S1 : std_logic;
	signal S2 : std_logic;
	signal S3 : std_logic;
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
   --constant <clock>_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: practica5 PORT MAP (
          G3 => G3,
          G2 => G2,
          G1 => G1,
          G0 => G0,
          P => P,
          S => S,
          T => T,
          C => C,
			 S0 => S0,
			 S1 => S1,
			 S2 => S2,
			 S3 => S3
        );

   -- Clock process definitions
   --<clock>_process :process
   --begin
		--<clock> <= '0';
		--wait for <clock>_period/2;
		--<clock> <= '1';
		--wait for <clock>_period/2;
   --end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for 100 ns;	

      --wait for <clock>_period*10;

      -- insert stimulus here 
		G3 <= '0'; G2 <= '0'; G1 <= '0'; G0 <= '0'; wait for 100 ns;
		G3 <= '0'; G2 <= '0'; G1 <= '0'; G0 <= '1'; wait for 100 ns;
		G3 <= '0'; G2 <= '0'; G1 <= '1'; G0 <= '1'; wait for 100 ns;
		G3 <= '0'; G2 <= '0'; G1 <= '1'; G0 <= '0'; wait for 100 ns;
		G3 <= '0'; G2 <= '1'; G1 <= '1'; G0 <= '0'; wait for 100 ns;
		G3 <= '0'; G2 <= '1'; G1 <= '1'; G0 <= '1'; wait for 100 ns;
		G3 <= '0'; G2 <= '1'; G1 <= '0'; G0 <= '1'; wait for 100 ns;
		G3 <= '0'; G2 <= '1'; G1 <= '0'; G0 <= '0'; wait for 100 ns;
		G3 <= '1'; G2 <= '1'; G1 <= '0'; G0 <= '0'; wait for 100 ns;
		G3 <= '1'; G2 <= '1'; G1 <= '0'; G0 <= '1'; wait for 100 ns;
		G3 <= '1'; G2 <= '1'; G1 <= '1'; G0 <= '1'; wait for 100 ns;
		G3 <= '1'; G2 <= '1'; G1 <= '1'; G0 <= '0'; wait for 100 ns;
		G3 <= '1'; G2 <= '0'; G1 <= '1'; G0 <= '0'; wait for 100 ns;
		G3 <= '1'; G2 <= '0'; G1 <= '1'; G0 <= '1'; wait for 100 ns;
		G3 <= '1'; G2 <= '0'; G1 <= '0'; G0 <= '1'; wait for 100 ns;
		G3 <= '1'; G2 <= '0'; G1 <= '0'; G0 <= '0'; wait for 100 ns;
      wait;
   end process;

END;