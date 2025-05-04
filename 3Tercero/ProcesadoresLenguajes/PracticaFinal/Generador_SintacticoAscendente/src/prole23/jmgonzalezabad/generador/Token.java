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



/**
 * Clase que describe un componente l�xico
 *  
 * * @author Francisco Jos� Moreno Velo
 *
 */
public class Token {
	
	/**
	 * Constante que identifica la categor�a l�xica de final de entrada
	 */
	public static final int EOF = 0;
	
	/**
	 * Tipo de componente l�xico.
	 * Identificador de la categor�a l�xica del componente.
	 */
	private int kind;
	
	/**
	 * Lexema que da origen al componente
	 */
	private String lexeme;
	
	/**
	 * N�mero de fila en la que se encuentra el inicio del componente
	 */
	private int row;
	
	/**
	 * N�mero de columna en la que se encuentra el inicio del componente
	 */
	private int column;
	

	/**
	 * Constructor
	 * @param kind Identificador de la categor�a l�xica
	 * @param lexeme Lexema que origina el componente
	 * @param row Fila en la que comienza el componente
	 * @param column Columna en la que comienza el componente
	 */
	public Token(int kind, String lexeme, int row, int column) 
	{
		this.kind = kind;
		this.lexeme = lexeme;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Obtiene el identificador de categor�a
	 * @return Tipo de componente
	 */
	public int getKind() 
	{
		return this.kind;
	}
	
	/**
	 * Obtiene el lexema del componente
	 * @return Lexema del componente
	 */
	public String getLexeme() 
	{
		return this.lexeme;
	}
	
	/**
	 * Obtiene la fila de inicio del componente
	 * @return Fila de inicio del componente
	 */
	public int getRow() 
	{
		return this.row;
	}
	
	/**
	 * Obtiene la columna de inicio del componente
	 * @return Columna de inicio del componente
	 */
	public int getColumn() 
	{
		return this.column;
	}
	
	/**
	 * Obtiene una descripci�n del Token
	 */
	public String toString() 
	{
		return "[Row: "+row+"][Column: "+column+"][Kind: "+kind+"] "+lexeme;
	}
}
