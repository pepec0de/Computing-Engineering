package prole23.jmgonzalezabad.generador;
//------------------------------------------------------------------//
//                        COPYRIGHT NOTICE                          //
//------------------------------------------------------------------//
// Copyright (c) 2017, Francisco Jos� Moreno Velo                   //
// All rights reserved.                                             //
//                                                                  //
// Redistribution and use in source and binary forms, with or       //
// without modification, are permitted provided that the following  //
// conditions are met:                                              //
//                                                                  //
// * Redistributions of source code must retain the above copyright //
//   notice, this list of conditions and the following disclaimer.  // 
//                                                                  //
// * Redistributions in binary form must reproduce the above        // 
//   copyright notice, this list of conditions and the following    // 
//   disclaimer in the documentation and/or other materials         // 
//   provided with the distribution.                                //
//                                                                  //
// * Neither the name of the University of Huelva nor the names of  //
//   its contributors may be used to endorse or promote products    //
//   derived from this software without specific prior written      // 
//   permission.                                                    //
//                                                                  //
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND           // 
// CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,      // 
// INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF         // 
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE         // 
// DISCLAIMED. IN NO EVENT SHALL THE COPRIGHT OWNER OR CONTRIBUTORS //
// BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,         // 
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED  //
// TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,    //
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND   // 
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT          //
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING   //
// IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF   //
// THE POSSIBILITY OF SUCH DAMAGE.                                  //
//------------------------------------------------------------------//



import java.util.Stack;

/**
 * Clase que desarrolla el comportamiento com�n de los analizadores
 * sint�cticos ascendentes basados en tablas SLR
 * 
 * @author Francisco Jos� Moreno Velo
 *
 */
public abstract class SLRParser {

	/**
	 * Analizador l�xico
	 */
	private Lexer lexer;
	
	/**
	 * Siguiente token de la cadena de entrada
	 */
	private Token nextToken;
	
	/**
	 * Pila de estados
	 */
	private Stack<Integer> stack;
	
	/**
	 * Tabla de acciones
	 */
	protected ActionElement[][] actionTable;
	
	/**
	 * Tabla de Ir_a
	 */
	protected int[][] gotoTable;
	
	/**
	 * Lista de reglas
	 */
	protected int[][] rule;
	
	/**
	 * Realiza el an�lisis sint�ctico a partir del l�xico
	 * @param filename
	 * @return
	 */
	protected boolean parse(Lexer lexer) throws SintaxException
	{
		this.lexer = lexer;
		this.nextToken = lexer.getNextToken();
		this.stack = new Stack<Integer>();
		this.stack.push(new Integer(0));
		while(true) 
		{
			if(step()) break;
		}
		return true;
	}
	
	/**
	 * M�todo que realiza una acci�n de desplazamiento
	 * @param action
	 */
	private void shiftAction(ActionElement action) 
	{
		nextToken = lexer.getNextToken();
		stack.add(new Integer(action.getValue()));
	}
	
	/**
	 * M�todo que realiza una acci�n de reducci�n
	 * @param action
	 */
	private void reduceAction(ActionElement action) 
	{
		int ruleIndex = action.getValue();
		int numSymbols = rule[ruleIndex][1];
		int leftSymbol = rule[ruleIndex][0];
		while(numSymbols > 0) 
		{
			stack.pop();
			numSymbols --;
		}
		int state = ((Integer) stack.lastElement()).intValue();
		int gotoState = gotoTable[state][leftSymbol];
		stack.push(new Integer(gotoState));
	}
	
	/**
	 * Ejecuta un paso en el an�lisis sint�ctico, es decir, extrae
	 * un elemento de la pila y lo analiza.
	 * @throws SintaxException
	 */
	private boolean step() throws SintaxException 
	{
		int state = ((Integer) stack.lastElement()).intValue();
		ActionElement action = actionTable[state][nextToken.getKind()];
		if(action == null) 
		{
			int count = 0;
			for(int i=0; i<actionTable[state].length; i++) if(actionTable[state][i] != null) count++;
			int[] expected = new int[count];
			for(int i=0,j=0; i<actionTable[state].length; i++) if(actionTable[state][i] != null) 
			{
				expected[j] = i;
				j++;
			}
			throw new SintaxException(nextToken,expected);
		}
		int actionType = action.getType();
		if(actionType == ActionElement.ACCEPT) 
		{
			return true;
		} 
		else if(actionType == ActionElement.SHIFT) 
		{
			shiftAction(action);
			return false;
		} 
		else if(actionType == ActionElement.REDUCE) 
		{
			reduceAction(action);
			return false;
		}
		return false;
	}
}
