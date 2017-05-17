package brugerautorisation.transport.soap;

import brugerautorisation.data.Diverse;
import brugerautorisation.data.Bruger;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author j
 */
public class Brugeradminklient {
	public static void main(String[] args) throws MalformedURLException {
//		URL url = new URL("http://localhost:9901/brugeradmin?wsdl");
		URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
		QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
		Service service = Service.create(url, qname);
		Brugeradmin ba = service.getPort(Brugeradmin.class);

    //ba.sendGlemtAdgangskodeEmail("jacno", "Dette er en test, husk at skifte 3712kode");
		//ba.ændrAdgangskode("jacno", "kode3stljl", "xxx");
		Bruger b = ba.hentBruger("s153712", "yas12!");
		System.out.println("Fik bruger = " + b);
		System.out.println("Data: " + Diverse.toString(b));
		// ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");
		ba.setEkstraFelt("s153712", "yas12!", "s123456_testfelt", "Jeg er så glad");
		Object ekstraFelt = ba.getEkstraFelt("s153712", "yas12!", "s123456_testfelt");
		System.out.println("Fik ekstraFelt = " + ekstraFelt);

	}
}
