package prole23.jmgonzalezabad.generador.output;
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

import prole23.jmgonzalezabad.generador.ActionElement;
import prole23.jmgonzalezabad.generador.SLRParser;

public class Parser extends SLRParser implements TokenConstants, SymbolConstants {

	public Parser() {
		initRules();
		initActionTable();
		initGotoTable();
	}

	private void initRules() {
		int[][] initRule = {
				{ 0, 0 } ,
				{ Expr, 1 },
				{ Expr, 3 },
				{ Expr, 3 },
				{ Term, 1 },
				{ Term, 3 },
				{ Term, 3 },
				{ Factor, 1 },
				{ Factor, 3 },
				{ Factor, 4 },
				{ Args, 1 },
				{ Args, 0 },
				{ ArgumentList, 1 },
				{ ArgumentList, 3 }
		};

		this.rule = initRule;
	}

	private void initActionTable() {
		actionTable = new ActionElement[24][10];

		actionTable[0][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[0][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[0][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[1][EOF] = new ActionElement(ActionElement.ACCEPT, 0);
		actionTable[1][PLUS] = new ActionElement(ActionElement.SHIFT, 7);
		actionTable[1][MINUS] = new ActionElement(ActionElement.SHIFT, 8);

		actionTable[2][EOF] = new ActionElement(ActionElement.REDUCE, 1);
		actionTable[2][PLUS] = new ActionElement(ActionElement.REDUCE, 1);
		actionTable[2][MINUS] = new ActionElement(ActionElement.REDUCE, 1);
		actionTable[2][PROD] = new ActionElement(ActionElement.SHIFT, 9);
		actionTable[2][DIV] = new ActionElement(ActionElement.SHIFT, 10);
		actionTable[2][RPAREN] = new ActionElement(ActionElement.REDUCE, 1);
		actionTable[2][COMMA] = new ActionElement(ActionElement.REDUCE, 1);

		actionTable[3][EOF] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][PLUS] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][MINUS] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][PROD] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][DIV] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][RPAREN] = new ActionElement(ActionElement.REDUCE, 4);
		actionTable[3][COMMA] = new ActionElement(ActionElement.REDUCE, 4);

		actionTable[4][EOF] = new ActionElement(ActionElement.REDUCE, 7);
		actionTable[4][PLUS] = new ActionElement(ActionElement.REDUCE, 7);
		actionTable[4][MINUS] = new ActionElement(ActionElement.REDUCE, 7);
		actionTable[4][PROD] = new ActionElement(ActionElement.REDUCE, 7);
		actionTable[4][DIV] = new ActionElement(ActionElement.REDUCE, 7); 
		actionTable[4][RPAREN] = new ActionElement(ActionElement.REDUCE, 7);
		actionTable[4][COMMA] = new ActionElement(ActionElement.REDUCE, 7);

		actionTable[5][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[5][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[5][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[6][LPAREN] = new ActionElement(ActionElement.SHIFT, 12);

		actionTable[7][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[7][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[7][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[8][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[8][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[8][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[9][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[9][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[9][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[10][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[10][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[10][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[11][PLUS] = new ActionElement(ActionElement.SHIFT, 7);
		actionTable[11][MINUS] = new ActionElement(ActionElement.SHIFT, 8);
		actionTable[11][RPAREN] = new ActionElement(ActionElement.SHIFT, 17);

		actionTable[12][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[12][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[11][RPAREN] = new ActionElement(ActionElement.REDUCE, 11);
		actionTable[12][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[13][EOF] = new ActionElement(ActionElement.REDUCE, 2);
		actionTable[13][PLUS] = new ActionElement(ActionElement.REDUCE, 2);
		actionTable[13][MINUS] = new ActionElement(ActionElement.REDUCE, 2);
		actionTable[13][PROD] = new ActionElement(ActionElement.SHIFT, 9);
		actionTable[13][DIV] = new ActionElement(ActionElement.SHIFT, 10);
		actionTable[13][RPAREN] = new ActionElement(ActionElement.REDUCE, 2);
		actionTable[13][COMMA] = new ActionElement(ActionElement.REDUCE, 2);

		actionTable[14][EOF] = new ActionElement(ActionElement.REDUCE, 3);
		actionTable[14][PLUS] = new ActionElement(ActionElement.REDUCE, 3);
		actionTable[14][MINUS] = new ActionElement(ActionElement.REDUCE, 3);
		actionTable[14][PROD] = new ActionElement(ActionElement.SHIFT, 9);
		actionTable[14][DIV] = new ActionElement(ActionElement.SHIFT, 10);
		actionTable[14][RPAREN] = new ActionElement(ActionElement.REDUCE, 3);
		actionTable[14][COMMA] = new ActionElement(ActionElement.REDUCE, 3);

		actionTable[15][EOF] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][PLUS] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][MINUS] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][PROD] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][DIV] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][RPAREN] = new ActionElement(ActionElement.REDUCE, 5);
		actionTable[15][COMMA] = new ActionElement(ActionElement.REDUCE, 5);

		actionTable[16][EOF] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][PLUS] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][MINUS] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][PROD] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][DIV] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][RPAREN] = new ActionElement(ActionElement.REDUCE, 6);
		actionTable[16][COMMA] = new ActionElement(ActionElement.REDUCE, 6);

		actionTable[17][EOF] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][PLUS] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][MINUS] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][PROD] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][DIV] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][RPAREN] = new ActionElement(ActionElement.REDUCE, 8);
		actionTable[17][COMMA] = new ActionElement(ActionElement.REDUCE, 8);

		actionTable[18][RPAREN] = new ActionElement(ActionElement.SHIFT, 21);

		actionTable[19][RPAREN] = new ActionElement(ActionElement.REDUCE, 10);
		actionTable[19][COMMA] = new ActionElement(ActionElement.SHIFT, 22);

		actionTable[20][PLUS] = new ActionElement(ActionElement.SHIFT, 7);
		actionTable[20][MINUS] = new ActionElement(ActionElement.SHIFT, 8);
		actionTable[20][RPAREN] = new ActionElement(ActionElement.REDUCE, 12);
		actionTable[20][COMMA] = new ActionElement(ActionElement.REDUCE, 12);

		actionTable[21][EOF] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][PLUS] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][MINUS] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][PROD] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][DIV] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][RPAREN] = new ActionElement(ActionElement.REDUCE, 9);
		actionTable[21][COMMA] = new ActionElement(ActionElement.REDUCE, 9);

		actionTable[22][NUM] = new ActionElement(ActionElement.SHIFT, 4);
		actionTable[22][LPAREN] = new ActionElement(ActionElement.SHIFT, 5);
		actionTable[22][IDENTIFIER] = new ActionElement(ActionElement.SHIFT, 6);

		actionTable[23][PLUS] = new ActionElement(ActionElement.SHIFT, 7);
		actionTable[23][MINUS] = new ActionElement(ActionElement.SHIFT, 8);
		actionTable[23][RPAREN] = new ActionElement(ActionElement.REDUCE, 13);
		actionTable[23][COMMA] = new ActionElement(ActionElement.REDUCE, 13);
	}

	private void initGotoTable() {
		gotoTable = new int[24][5];

		gotoTable[0][Expr] = 1;
		gotoTable[0][Term] = 2;
		gotoTable[0][Factor] = 3;
		
		gotoTable[5][Expr] = 11;
		gotoTable[5][Term] = 2;
		gotoTable[5][Factor] = 3;
		
		gotoTable[7][Term] = 13;
		gotoTable[7][Factor] = 3;
	
		gotoTable[8][Term] = 14;
		gotoTable[8][Factor] = 3;

		gotoTable[9][Factor] = 15;
	
		gotoTable[10][Factor] = 16;

		gotoTable[12][Expr] = 20;
		gotoTable[12][Term] = 2;
		gotoTable[12][Factor] = 3;
		gotoTable[12][Args] = 18;
		gotoTable[12][ArgumentList] = 19;

		gotoTable[22][Expr] = 23;
		gotoTable[22][Term] = 2;
		gotoTable[22][Factor] = 3;

	}
}