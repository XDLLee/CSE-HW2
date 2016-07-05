import java.util.*;

public class HtmlValidator {
   private Queue<HtmlTag> validator; //
   public final String INDENTATION = "    ";
   
   //Construct a new empty validating Queue
   public HtmlValidator() {
      validator = new LinkedList<HtmlTag>();
   }
   
   //pre : Passed Queue is not null;
   //      Otherwise throw IllegalArgumentException
   //
   //post: Construct a validating Queue based on passed Queue storing tags
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
   
   //pre : Passed tag is not null;
   //      Otherwise throw IllegalArgumentException
   //
   //post: Add the passed tag to the end of the validating Queue
   public void addTag(HtmlTag tag) {
      if (tag == null) {
         throw new IllegalArgumentException();
      }
      validator.add(tag);
   }
   
   //Return a copy of the validating Queue
   public Queue<HtmlTag> getTags() {
      Queue<HtmlTag> result = new LinkedList<HtmlTag>();
      for (int i = 0; i < validator.size(); i++) {
         result.add(validator.peek());
         validator.add(validator.remove());
      }
      return result;
   }
   
   //pre : Passed String element is not null;
   //      Otherwise throw IllegalArgumentException
   //
   //post: Remove all tags which excatly match the passed String
   //      in the validating Queue
   public void removeAll(String element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      element = element.toLowerCase();
      int size = validator.size();
      for (int i = 0; i < size; i++) {
         if (validator.peek().getElement().equals(element)) {
            validator.remove();
         } 
         else {
            validator.add(validator.remove());
         }
      }
   }
   
   //Print out and evaluate stored tags in following rules:
   //Every tag is displayed in a single line;
   //
   //Each time see an opening tag which requires a closing tag,
   //increase level of Indentation;
   //
   //Each time see a closing tag matches nearest opening tag,
   //decrease level of Indentation,
   //otherwise print "ERROR unexpected tag";
   //
   //Each time see a Self-closing tag which is not an opening tag, 
   //print "ERROR unexpected tag";
   //
   //Print "ERROR unclosed tag" for every unclosed opening tag
   public void validate() {
      Stack<HtmlTag> temp = new Stack<HtmlTag>();
      int size = validator.size();
      int countOpenTag = 0;
      for (int i = 0; i < size; i++) {
         HtmlTag tag = validator.remove();
         if (tag.isSelfClosing()) {
            if (!tag.isOpenTag()) {
               System.out.println(unexpected(tag));
            } 
            else { // Self-closing && Opening tag
               System.out.println(printLine(countOpenTag, tag));
            }
         } 
         else if (tag.isOpenTag()) {
            temp.push(tag);
            System.out.println(printLine(countOpenTag, tag));
            countOpenTag++;
         } 
         else { // Current tag is closing tag
            if (temp.isEmpty() || !tag.matches(temp.peek())) {
               System.out.println(unexpected(tag));
            } 
            else { // Current tag matches stored opening tag
               temp.pop();
               countOpenTag--;
               System.out.println(printLine(countOpenTag, tag));
            }
         }
         validator.add(tag);
      }
      while (!temp.isEmpty()) {
         System.out.println(unclosed(temp.pop()));
      }
   }
   
   //Print tags with passed number of Indentations
   private String printLine(int num, HtmlTag element) {
      String result = "";
      for (int i = 0; i < num; i++) {
         result += INDENTATION;
      }
      result += element.toString();
      return result;
   }
   
   //Unexpected error String
   private String unexpected(HtmlTag tag) {
      return "ERROR unexpected tag: " + tag.toString();
   }
   
   //Unclosed error String
   private String unclosed(HtmlTag tag) {
      return "ERROR unclosed tag: " + tag.toString();
   }
}