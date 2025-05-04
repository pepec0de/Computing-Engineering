--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   13:59:23 01/16/2021
-- Design Name:   
-- Module Name:   /home/pepe/Documentos/Universidad/UWU/Asignaturas/Fundamentos de computadores/Xilinx ISE Projects/Practica7/test.vhd
-- Project Name:  Practica7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: practica7
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
 
    COMPONENT practica7
    PORT(
         M : IN  std_logic;
         FC1 : IN  std_logic;
         FC2 : IN  std_logic;
         FC3 : IN  std_logic;
         FC4 : IN  std_logic;
         CK : IN  std_logic;
         RS : IN  std_logic;
         T : OUT  std_logic;
         EP1 : OUT  std_logic;
         EP2 : OUT  std_logic;
         RP1 : OUT  std_logic;
         RP2 : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal M : std_logic := '0';
   signal FC1 : std_logic := '0';
   signal FC2 : std_logic := '0';
   signal FC3 : std_logic := '0';
   signal FC4 : std_logic := '0';
   signal CK : std_logic := '0';
   signal RS : std_logic := '0';

 	--Outputs
   signal T : std_logic;
   signal EP1 : std_logic;
   signal EP2 : std_logic;
   signal RP1 : std_logic;
   signal RP2 : std_logic;
   -- No clocks detected in port list. Replace CK below with 
   -- appropriate port name 
 
   constant CK_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: practica7 PORT MAP (
          M => M,
          FC1 => FC1,
          FC2 => FC2,
          FC3 => FC3,
          FC4 => FC4,
          CK => CK,
          RS => RS,
          T => T,
          EP1 => EP1,
          EP2 => EP2,
          RP1 => RP1,
          RP2 => RP2
        );

   -- Clock process definitions
   CK_process :process
   begin
		CK <= '0';
		wait for CK_period/2;
		CK <= '1';
		wait for CK_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      --wait for 100 ns;	

      --wait for CK_period*10;

      -- insert stimulus here 
		RS <= '0'; wait for 20 ns;
		RS <= '1'; wait for 35 ns;
		RS <= '0'; wait for 20 ns;
		
		M <= '0'; wait for 40 ns;
		M <= '1'; wait for 40 ns;
		M <= '0'; wait for 40 ns;
		
		FC1 <= '1'; FC2 <= '0'; FC3 <= '1'; FC4 <= '0'; wait for 40 ns;
		FC1 <= '1'; FC2 <= '0'; FC3 <= '1'; FC4 <= '0'; wait for 40 ns;
		
		FC1 <= '1'; FC2 <= '0'; FC3 <= '1'; FC4 <= '0'; wait for 40 ns;
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '0'; wait for 40 ns;
		
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '0'; wait for 40 ns;
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '0'; wait for 40 ns;
		
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '0'; wait for 40 ns;
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '1'; wait for 40 ns;
		
		FC1 <= '0'; FC2 <= '0'; FC3 <= '0'; FC4 <= '1'; wait for 40 ns;
		FC1 <= '0'; FC2 <= '1'; FC3 <= '0'; FC4 <= '1'; wait for 40 ns;
		
		FC1 <= '0'; FC2 <= '1'; FC3 <= '0'; FC4 <= '1'; wait for 40 ns;
		FC1 <= '0'; FC2 <= '0'; FC3 <= '0'; FC4 <= '1'; wait for 40 ns;
		
		FC1 <= '1'; FC2 <= '0'; FC3 <= '0'; FC4 <= '0'; wait for 40 ns;
		FC1 <= '1'; FC2 <= '0'; FC3 <= '1'; FC4 <= '0'; wait for 40 ns;
      wait;
   end process;

END;
