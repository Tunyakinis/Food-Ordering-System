package com.tunyakinis.command;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InstructionRunner {

  @PostConstruct
  public void init() {
    List<String> instructionList = new ArrayList<>();
    instructionList.add("Enter 1 to display lunch");
    instructionList.add("Enter 2 to display drinks");
    instructionList.add("Enter 3 to order drinks");
    instructionList.add("Enter 4 to order food");
    instructionList.add("Enter 5 to submit order");
    instructionList.add("Enter 6 to clean bucket to cancel current order");
    instructionList.add("Enter 7 to show all clients' orders");
    instructionList.add("Enter 'help' to view all possible commands");
    for (String item : instructionList) {
      System.out.println(item);
    }
  }
}
