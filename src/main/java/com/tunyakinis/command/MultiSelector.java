package com.tunyakinis.command;

import com.tunyakinis.model.Drink;
import com.tunyakinis.model.EnumAddition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.component.MultiItemSelector;
import org.springframework.shell.component.MultiItemSelector.MultiItemSelectorContext;
import org.springframework.shell.component.support.Itemable;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
@RequiredArgsConstructor
public class MultiSelector extends AbstractShellComponent {

  public List<String> multiselectDrinks(List<Drink> drinkList) {
    List<SelectorItem<String>> items = drinkList.stream()
        .map(drink -> SelectorItem.of(
            drink.getName() + " = " + drink.getPrice().toString(), drink.getName()))
        .collect(Collectors.toList());

    MultiItemSelectorContext<String, SelectorItem<String>> context = runMultiselect(items);

    return context.getResultItems().stream()
        .map(Itemable::getItem)
        .collect(Collectors.toList());
  }

  public Map<String, String> multiselectAdditionForDrinks(List<String> selectedDrinks) {
    Map<String, String> result = new HashMap<>();

    selectedDrinks.forEach(drink -> {
      System.out.println("Do you need addition for " + drink + "?");

      List<SelectorItem<String>> items = Arrays.stream(EnumAddition.values())
          .map(addition -> SelectorItem.of(addition.getAddition(), addition.getAddition()))
          .collect(Collectors.toList());

      MultiItemSelectorContext<String, SelectorItem<String>> context = runMultiselect(items);

      String contextString = context.getResultItems().stream()
          .map(Itemable::getItem)
          .collect(Collectors.joining(", "));

      result.put(drink, contextString);
    });

    return result;
  }

  private MultiItemSelectorContext<String, SelectorItem<String>> runMultiselect(
      List<SelectorItem<String>> items) {
    MultiItemSelector<String, SelectorItem<String>> component = new MultiItemSelector<>(
        getTerminal(), items, "[Use Space button to choose, Enter to confirm], ", null);

    component.setResourceLoader(getResourceLoader());
    component.setTemplateExecutor(getTemplateExecutor());

    return component.run(MultiItemSelectorContext.empty());
  }
}
