--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   16:22:06 01/11/2021
-- Design Name:   
-- Module Name:   /home/pepe/Documentos/Universidad/UWU/Asignaturas/Fundamentos de computadores/Xilinx ISE Projects/Practica6/test.vhd
-- Project Name:  Practica6
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: practica6
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
 
    COMPONENT practica6
    PORT(
         S : IN  std_logic_vector(1 downto 0);
         M : IN  std_logic;
         A : IN  std_logic_vector(3 downto 0);
         B : IN  std_logic_vector(3 downto 0);
         CI : IN  std_logic;
         F : OUT  std_logic_vector(3 downto 0);
         CO : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal S : std_logic_vector(1 downto 0) := (others => '0');
   signal M : std_logic := '0';
   signal A : std_logic_vector(3 downto 0) := (others => '0');
   signal B : std_logic_vector(3 downto 0) := (others => '0');
   signal CI : std_logic := '0';

 	--Outputs
   signal F : std_logic_vector(3 downto 0);
   signal CO : std_logic;
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
   --constant <clock>_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: practica6 PORT MAP (
          S => S,
          M => M,
          A => A,
          B => B,
          CI => CI,
          F => F,
          CO => CO
        );

   -- Clock process definitions
--   <clock>_process :process
--   begin
--		<clock> <= '0';
--		wait for <clock>_period/2;
--		<clock> <= '1';
--		wait for <clock>_period/2;
--   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      --wait for 100 ns;	

      --wait for <clock>_period*10;

      -- insert stimulus here 
		A <= "0101";
		B <= "0010";
		CI <= '0';
		M <= '0' ; S <= "00" ; wait for 10 ns;
		M <= '0' ; S <= "01" ; wait for 10 ns;
		M <= '0' ; S <= "10" ; wait for 10 ns;
		M <= '0' ; S <= "11" ; wait for 10 ns;
		M <= '1' ; S <= "00" ; wait for 10 ns;
		M <= '1' ; S <= "01" ; wait for 10 ns;
		M <= '1' ; S <= "10" ; wait for 10 ns;
		M <= '1' ; S <= "11" ; wait for 10 ns;
		CI <= '1';
		M <= '0' ; S <= "00" ; wait for 10 ns;
		M <= '0' ; S <= "01" ; wait for 10 ns;
		M <= '0' ; S <= "10" ; wait for 10 ns;
		M <= '0' ; S <= "11" ; wait for 10 ns;
		M <= '1' ; S <= "00" ; wait for 10 ns;
		M <= '1' ; S <= "01" ; wait for 10 ns;
		M <= '1' ; S <= "10" ; wait for 10 ns;
		M <= '1' ; S <= "11" ; wait for 10 ns;
      wait;
   end process;

END;
