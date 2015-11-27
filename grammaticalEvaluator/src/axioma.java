import java.util.Scanner;
import java.util.Stack;

/**
 * Gramática
 *      Axioma E
 *      Terminales {E, EP, T, TP, F}
 *      No terminales {;, +, -, *, /, (, num, id]
 *      Reglas
 *          E -> T EP ;
 *          EP -> +T EP | -T EP | ¢
 *          T -> F TP
 *          TP -> *F TP | /F TP | ¢
 *          F -> (E) | num | id
 *      NOTA: num es un número entero (0,1,2,3,4,5,6,7,8,9)
 * @author Santiago RG
 */

public class axioma {
	// Pila para evaluar la expresion
    public Stack st = new Stack();
	// Entrada por teclado
	public static String input = "";
	// Indica el caracter analizado de la entrada
    public int actual = 0;
    
    /**
     * valida si el token actual es un digito
     */ 
    public boolean tokenActualEsUnDigito(){
        return  (input.charAt(actual) >= '0'
                && input.charAt(actual) <= '9');
    }
	
    /**
     * valida si el token actual es igual al esperado
     * si es verdad pasa al siguiente token
     * en caso contrario genera error
     * @param expected 
     */
    
    public void match( char expected){
        // espero un número
        if( expected == '#'  ) {
            if (tokenActualEsUnDigito()) {
                    actual++;
                    return;
            }
        } else if (input.charAt(actual) == expected ) {
                    actual++;
                    return;
        }
        System.out.println("Esperaba un "+expected);
        System.exit(0);
    }
    
    public void proc_E() { // Digito Entero
        if (tokenActualEsUnDigito()) {            
            proc_T();
            proc_EP();            
        } else {
            System.out.println("Esparaba un digito del cero al nueve");
        }
        
    }
    
    public void proc_EP() {
        if (input.charAt(actual) == '+') {
            match('+');
            // ejecutar el codigo push
            int entero = input.charAt(actual) - '0';
            st.push(new Integer(entero)); 
            match('#');
            // Realizo la suma
            int a,b;
            a = ((Integer)st.pop()).intValue();
            b = ((Integer)st.pop()).intValue();
            st.push(new Integer( b + a ));
            proc_EP();
        }
        else if ( input.charAt(actual) == '-') {
            match('-');
            // ejecutar el codigo push
            int entero = input.charAt(actual) - '0';
            st.push(new Integer(entero)); 
            match('#');
            // Realizo la resta
            int a,b;
            a = ((Integer)st.pop()).intValue();
            b = ((Integer)st.pop()).intValue();
            st.push(new Integer( b - a ));
            proc_EP();
        }
    }
    
    public void proc_T(){
    	if (tokenActualEsUnDigito()) {            
            proc_F();
            proc_TP();            
        } else {
            System.out.println("Esparaba un digito del cero al nueve");
        }
    }
    
    public void proc_TP() {
        if (input.charAt(actual) == '*') {
            match('*');
            // ejecutar el codigo push
            int entero = input.charAt(actual) - '0';
            st.push(new Integer(entero)); 
            match('#');
            // Realizo la multiplicación
            int a,b;
            a = ((Integer)st.pop()).intValue();
            b = ((Integer)st.pop()).intValue();
            st.push(new Integer( b * a ));
            proc_TP();
        }
        else if ( input.charAt(actual) == '/') {
            match('/');
            // ejecutar el codigo push
            int entero = input.charAt(actual) - '0';
            st.push(new Integer(entero)); 
            match('#');
            // Realizo la división
            int a,b;
            a = ((Integer)st.pop()).intValue();
            b = ((Integer)st.pop()).intValue();           
            st.push(new Integer( b / a ));                     
            proc_TP();
        }
    }
    
    public void proc_F(){
    	if (tokenActualEsUnDigito()) {            
            // ejecutar el codigo push
            int entero = input.charAt(actual) - '0';
            st.push(new Integer(entero)); 
            match('#');
        } else {                       
            proc_E();            
        }
    }
    
	public static void main(String[] args) {	
		axioma a = new axioma();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String: ");
		input = sc.next();
		input += "$";
		a.proc_E();// Llamar el Axioma
        if (a.input.charAt(a.actual) != '$') {
            System.out.println("Error de Compilación");
        }
        else {
            System.out.println("Terminó, Cadena válida!");
            int at=0;
            at=((Integer)a.st.pop()).intValue();
            System.out.println("resultado:"+at);
        }		        
	}

}
