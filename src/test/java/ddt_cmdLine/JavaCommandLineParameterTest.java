package ddt_cmdLine;

public class JavaCommandLineParameterTest {

	public static void main(String[] args) {
//		To test the run-time paratmeters using Eclipse command or command line parameter
		
		System.out.println(args.length);
		for(String str:args) {
			System.out.println(str);
		}
		
		String browser = args[0];
		String url = args[1];
		String username = args[2];
		String password = args[3];
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		System.out.println(args[3]);

		
/* We can also pass the parameters inside selenium:
click on Run As> Run Configurations, a pop-up will open, click on Arguments tab
and pass the parameters inside 'Program arguments' box such as URL, username, password with single space
*/

}
}
