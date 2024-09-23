package sample;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class MeteoWeb {
    public MeteoWeb() throws IOException, URISyntaxException {
        // TODO Auto-generated method stub
        String url="http://www.meteomaroc.com/";
        Desktop d=Desktop.getDesktop();
        d.browse(new URI(url));
    }

}
