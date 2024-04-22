package ddt_cmdLine;

public class JavaCommandLineParameterTest {

	public static void main(String[] args) {
//		To test the run-time paratmeters using Eclipse command or command line parameter
		
		System.out.println(args.length);
		for(String str:args) {
			System.out.println(str);
		}
		
/* We can also pass the parameters inside selenium:
click on Run As> Run Configurations, a pop-up will open, click on Arguments tab
and pass the parameters inside 'Program arguments' box such as URL, username, password with single space
*/

}
}
