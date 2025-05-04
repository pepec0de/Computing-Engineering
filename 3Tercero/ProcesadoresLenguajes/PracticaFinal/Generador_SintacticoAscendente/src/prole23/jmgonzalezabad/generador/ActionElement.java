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
 * Elemento de la tabla de acci�n
 * 
 * @author Francisco Jos� Moreno Velo
 */
public class ActionElement {

	/**
	 * Constante que identifica un elemento de tipo aceptar
	 */
	public static final int ACCEPT = 0;
	
	/**
	 * Constannte que identifica un elemento de tipo shift
	 */
	public static final int SHIFT = 1;
	
	/**
	 * Constante que identifica un elemento de tipo reduce
	 */
	public static final int REDUCE = 2;
	
	/**
	 * Tipo de elemento (ACEPT, SHIFT o REDUCE)
	 */
	private int type;
	
	/**
	 * Valor del elemento
	 */
	private int value;
	
	/**
	 * Constructor
	 * @param kind
	 */
	public ActionElement(int type, int value) 
	{
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Obtiene el tipo de elemento: ACEPT, SHIFT o REDUCE
	 * @return
	 */
	public int getType() 
	{
		return type;
	}
	
	/**
	 * Obtiene el valor (estado a apilar o regla a reducir)
	 * @return
	 */
	public int getValue() 
	{
		return value;
	}
}
