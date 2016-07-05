// This testing program stub creates a queue of HTML tags 
// in a valid sequence.
// You may use this as a starting point for being a client of your
// HtmlValidator object
import java.util.*;

public class HtmlValidatorTest {
	public static void main(String[] args) {
		// <b>Hi</b><br/>
		// A Queue of tags you may modify and pass to your HtmlValidator object
		Queue<HtmlTag> tags = new LinkedList<HtmlTag>();
		tags.add(new HtmlTag("b", true));      // <b>
		tags.add(new HtmlTag("b", false));     // </b>
		tags.add(new HtmlTag("br"));           // <br/>
		
		// Your code here
      HtmlTag tag1 = new HtmlTag("!doctype");
      HtmlTag tag2 = new HtmlTag("img");
      HtmlTag tag3 = new HtmlTag("br", false);
      
      HtmlValidator validator1 = new HtmlValidator();
      
      HtmlValidator validator2 = new HtmlValidator(tags);
      
      validator1.addTag(new HtmlTag("html", true));
      validator1.addTag(new HtmlTag("b", true));
      validator1.addTag(new HtmlTag("i", true));
      validator1.addTag(new HtmlTag("i", false));
      validator1.addTag(new HtmlTag("html", false));
      validator1.addTag(new HtmlTag("b", false));
      validator1.addTag(new HtmlTag("b", true));
      validator1.addTag(new HtmlTag("i", true));
      System.out.println(validator1.getTags());
      validator1.validate();
      System.out.println();
      validator1.removeAll("i");
      System.out.println(validator1.getTags());
      validator1.validate();
      System.out.println();
      
      validator2.addTag(tag1);
      validator2.addTag(tag2);
      validator2.addTag(tag3);
      System.out.println(validator2.getTags());
      validator2.validate();
      System.out.println();
      validator2.removeAll("b");
      System.out.println(validator2.getTags());
      System.out.println();
      
      HtmlValidator validator3 = new HtmlValidator();
      validator3.addTag(new HtmlTag("i", true));
      validator3.addTag(new HtmlTag("b", true));
      validator3.addTag(new HtmlTag("b", false));
      validator3.addTag(new HtmlTag("i", false));
      validator3.addTag(new HtmlTag("html", false));
      validator3.addTag(new HtmlTag("body", false));
      validator3.addTag(new HtmlTag("title", false));
      validator3.validate();
	}
}
