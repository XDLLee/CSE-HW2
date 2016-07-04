import java.util.*;

public class HtmlValidator {
   private Queue<HtmlTag> validator;
   public final String INDENTATION = "    ";
   
   public HtmlValidator() {
      validator = new LinkedList<HtmlTag>();
   }
   
   public HtmlValidator(Queue<HtmlTag> tag) {
      if (tag == null) {
         throw new IllegalArgumentException();
      }
      validator = new LinkedList<HtmlTag>();
      for (int i = 0; i < tag.size(); i++) {
         validator.add(tag.peek());
         tag.add(tag.remove());
      }
   }
   
   public void addTag(HtmlTag tag) {
      if (tag == null) {
         throw new IllegalArgumentException();
      }
      validator.add(tag);
   }
   
   public Queue<HtmlTag> getTags() {
      Queue<HtmlTag> result = new LinkedList<HtmlTag>();
      for (int i = 0; i < validator.size(); i++) {
         result.add(validator.peek());
         validator.add(validator.remove());
      }
      return result;
   }
   
   public void removeAll(String element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      element = element.toLowerCase();
      int size = validator.size();
      for (int i = 0; i < size; i++) {
         if (validator.peek().getElement().equals(element)) {
            validator.remove();
         } else {
            validator.add(validator.remove());
         }
      }
   }
   
   public void validate() {
      Stack temp = new Stack<HtmlTag>();
      int size = validator.size();
      int countOpenTag = 0;
      for (int i = 0; i < size; i++) {
         HtmlTag tag = validator.remove();
         if (tag.isSelfClosing()) {
            if (!tag.isOpenTag()) {
               System.out.println(unexpected(tag));
            } else { // Self-closing tag
               System.out.println(printLine(countOpenTag, tag));
            }
         } else if (tag.isOpenTag()) {
            temp.push(tag);
            System.out.println(printLine(countOpenTag, tag));
            countOpenTag++;
         } else { // Closing tag
            if (!tag.matches(temp.peek())) {
               System.out.println(unexpected(tag));
            } else { // Current tag closes stored tag
               temp.pop();
               countOpenTag--;
               System.out.println(printLine(countOpenTag, tag));
            }
         }
      }
      while (!temp.isEmpty()) {
         System.out.println(unclosed(temp.pop()));
      }
   }
   
   private String printLine(int num, HtmlTag element) {
      String result = "";
      for (int i = 0; i < num; i++) {
         result += INDENTATION;
      }
      result += element.toString();
      return result;
   }
   
   private String unexpected(HtmlTag tag) {
      return "ERROR unexpected tag: " + tag.toString();
   }
   
   private String unclosed(HtmlTag tag) {
      return "ERROR unclosed tag: " + tag.toString();
   }
}