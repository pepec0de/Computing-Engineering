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



import java.io.*;

import prole23.jmgonzalezabad.generador.output.TokenConstants;

/**
 * Clase que desarrolla el analizador l�xico de Tinto
 * 
 * @author Francisco Jos� Moreno Velo
 *
 */
public class ExprLexer extends Lexer implements TokenConstants {
	
	/**
	 * Constructor
	 * @param filename Nombre del fichero fuente
	 * @throws IOException En caso de problemas con el flujo de entrada
	 */
	public ExprLexer(File file) throws IOException 
	{
		super(file);
	}
	
	/**
	 * Transiciones del aut�mata
	 */
	protected int transition(int state, char symbol) {
		switch(state) {
			case 0:
				if(symbol == ' ' || symbol == '\t') return 1;
				if(symbol == '\r' || symbol == '\n') return 1;
				if(symbol >= 'a' && symbol <= 'z') return 2;
				if(symbol >= 'A' && symbol <= 'Z') return 2;
				if(symbol == '_') return 2;
				if(symbol == '0') return 3;
				if(symbol >= '1' && symbol <= '9') return 7;
				if(symbol == '(') return 12;
				if(symbol == ')') return 13;
				if(symbol == ',') return 14;
				if(symbol == '+') return 15;
				if(symbol == '-') return 16;
				if(symbol == '*') return 17;
				if(symbol == '/') return 18;
				return -1;
			case 1:
				if(symbol == ' ' || symbol == '\t') return 1;
				if(symbol == '\r' || symbol == '\n') return 1;
				return -1;
			case 2:
				if(symbol == '_') return 2;
				if(symbol >= 'a' && symbol <= 'z') return 2;
				if(symbol >= 'A' && symbol <= 'Z') return 2;
				if(symbol >= '0' && symbol <= '9') return 2;
				return -1;
			case 3:
				if(symbol == 'x' || symbol == 'X') return 4;
				if(symbol >= '0' && symbol <= '7') return 6;
				if(symbol == 'e' || symbol == 'E') return 9;
				if(symbol == '.') return 8;
				return -1;
			case 4:
				if(symbol >= '0' && symbol <= '9') return 5;
				if(symbol >= 'a' && symbol <= 'f') return 5;
				if(symbol >= 'A' && symbol <= 'F') return 5;
				return -1;
			case 5:
				if(symbol >= '0' && symbol <= '9') return 5;
				if(symbol >= 'a' && symbol <= 'f') return 5;
				if(symbol >= 'A' && symbol <= 'F') return 5;
				return -1;
			case 6:
				if(symbol >= '0' && symbol <= '7') return 6;
				return -1;
			case 7:
				if(symbol >= '0' && symbol <= '9') return 7;
				if(symbol == '.') return 8;
				if(symbol == 'e' || symbol == 'E') return 9;
				return -1;
			case 8:
				if(symbol >= '0' && symbol <= '9') return 8;
				if(symbol == 'e' || symbol == 'E') return 9;
				return -1;
			case 9:
				if(symbol >= '0' && symbol <= '9') return 11;
				if(symbol == '+' || symbol == '-') return 10;
				return -1;
			case 10:
				if(symbol >= '0' && symbol <= '9') return 11;
				return -1;
			case 11:
				if(symbol >= '0' && symbol <= '9') return 11;
				return -1;
			default: return -1;
		}
	}


	/**
	 * Estados finales del aut�mata
	 */
	protected boolean isFinal(int state) {
		if(state <0 || state >= 18) return false;
		switch(state) {
			case 0:
			case 4:
			case 9:
			case 10:
				return false;
			default:
				return true;
		}
	}

	/**
	 * Acciones de la MDD
	 */
	protected Token getToken(int state, String lexeme, int row, int column) {
		switch(state) {
			case 2: return new Token(IDENTIFIER,lexeme,row,column);
			case 3: return new Token(NUM,lexeme,row,column);
			case 5: return new Token(NUM,lexeme,row,column);
			case 6: return new Token(NUM,lexeme,row,column);
			case 7: return new Token(NUM,lexeme,row,column);
			case 8: return new Token(NUM,lexeme,row,column);
			case 11: return new Token(NUM,lexeme,row,column);
			case 12: return new Token(LPAREN,lexeme,row,column);
			case 13: return new Token(RPAREN,lexeme,row,column);
			case 14: return new Token(COMMA,lexeme,row,column);
			case 15: return new Token(PLUS,lexeme,row,column);
			case 16: return new Token(MINUS,lexeme,row,column);
			case 17: return new Token(PROD,lexeme,row,column);
			case 18: return new Token(DIV,lexeme,row,column);
			default: return null;
		}
	}

}
