package stoppuhrFXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.text.Font;

public class GoogleWebFontHelper {
    private static final Pattern TTF_PATTERN = Pattern.compile("https:.*\\.ttf");
    private static final Pattern OTF_PATTERN = Pattern.compile("https:.*\\.otf");

    /**
     * Load a font from Google Web Fonts Service
     *
     * Note: this is only designed to work with simple stylesheets with single
     * font in them.
     *
     * @param googleFontCssUrl The url to css stylesheet with @fontface definition
     * @return The name of the font family, or "System" if there was a error
     */
    public static String loadFont(String googleFontCssUrl) {
        String fontName = "System";
        try {
            URL cssUrl = new URL(googleFontCssUrl);
            URLConnection con = cssUrl.openConnection();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = null;
            String fontUrl = null;
            StringBuilder srcs = new StringBuilder();
            while((line = reader.readLine()) != null) {
               System.out.println(line);
                if (line.contains("src:")) {
                    Matcher matcher = TTF_PATTERN.matcher(line);
                    if (matcher.find()) {
                        fontUrl = matcher.group(0);
                        System.out.println("FOUND fontUrl = " + fontUrl);
                        break;
                    }
                    matcher = OTF_PATTERN.matcher(line);
                    if (matcher.find()) {
                        fontUrl = matcher.group(0);
                        System.out.println("FOUND fontUrl = " + fontUrl);
                        break;
                    }
                    srcs.append(line);
                    srcs.append('\n');
                }
            }
            if (fontUrl != null) {
                Font font = Font.loadFont(fontUrl, 10);
                fontName = font.getFamily();
            } else {
                System.err.println("Failed to find any supported fonts "
                        + "(TTF or OTF) in CSS src:\n"+srcs.toString());
            }
        } catch (IOException ex) {
            System.err.println("Error loading Google Font CSS");
        }
        return fontName;
    }
}

