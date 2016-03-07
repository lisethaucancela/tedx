/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recursos;

/**
 *
 * @author Diego
 */
public class Validaciones {
    private String fnumero;
   

   public static  int EvaluarCaracter(char Caracter)
		{
			return (int)Caracter - 48;
		}
   public Validaciones(String numero)
		{
			this.fnumero= numero;
			
		}
   public static  boolean ValidarNumeros(String NumeroCedula)
		{
			try
			{
				boolean blnValida = true;

				for (int i = 0; i <= 8; i++)
				{
					if (!Character.isDigit(NumeroCedula.charAt(i)))
					{
						blnValida = false;
					}
				}
				if (!(Character.isDigit(NumeroCedula.charAt(10))))
                                {blnValida = false;}
				return blnValida;
			}
			catch (Exception e)
			{
//				Errores err = new Errores();
//				err.SetError(fUbicacion, "Cedula", "private bool ValidarNumeros(string NumeroCedula)",e.Message);
				return false;
			}
		}
 //  private String Numero;
		
   public static boolean ValidaCedula(String numero)
		{
			try
			{
				if (numero.toString().length() == 11)
				{
					int	j, SumaP = 0, SumaI =0, SumaT, N1, Digito, Residuo;

					if (ValidarNumeros(numero))
					{
						for (int i = 0; i<=8; i++)
						{
							j = i + 1;
							if ((j % 2) == 1)
							{
								N1 = EvaluarCaracter(numero.charAt(i))*2;
                                                             
								if (N1 >= 10) N1 -= 9;
								SumaI = SumaI + N1;
							}
							if ((j % 2) == 0)
							{
								SumaP += EvaluarCaracter(numero.charAt(i));
                                                              
							}
						} // for
						SumaT = SumaP + SumaI;
						Residuo = SumaT % 10;

						if (Residuo == 0) Digito = 0;
						else Digito = 10 - Residuo;

						return ( Digito == EvaluarCaracter(numero.charAt(10)) );
					}// if
					else return false;
				}
				else return false;
			}
			catch (Exception e)
			{
//				Errores err = new Errores();
//				err.SetError(fUbicacion, "Cedula", "booleaneanean Valida()",e.Message);
				return false;
			}
		}

    /**
     * @return the fnumero
     */
    public String getFnumero() {
        return fnumero;
    }

    /**
     * @param fnumero the fnumero to set
     */
    public void setFnumero(String fnumero) {
        this.fnumero = fnumero;
    }

   

}
