package com.tunyakinis.command;


import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Meal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.component.SingleItemSelector;
import org.springframework.shell.component.SingleItemSelector.SingleItemSelectorContext;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
@RequiredArgsConstructor
public class SingleSelector extends AbstractShellComponent {

  public String singleCuisineSelector(List<Cuisine> cuisineList) {
    List<SelectorItem<String>> items = cuisineList.stream()
        .map(cuisine -> SelectorItem.of(cuisine.getName(), cuisine.getName()))
        .collect(Collectors.toList());

    return runSingleSelect(items);
  }

  public String singleMainDishSelector(List<Meal> mainCourseList) {
    List<SelectorItem<String>> items = mainCourseList.stream()
        .map(mainCourse -> SelectorItem.of(
            mainCourse.getName() + " = " + mainCourse.getPrice().toString(), mainCourse.getName()))
        .collect(Collectors.toList());

    return runSingleSelect(items);
  }

  public String singleDessertSelector(List<Meal> dessertList) {
    List<SelectorItem<String>> items = dessertList.stream()
        .map(dessert -> SelectorItem.of(dessert.getName() + " = " + dessert.getPrice().toString(),
            dessert.getName()))
        .collect(Collectors.toList());

    return runSingleSelect(items);
  }

  private String runSingleSelect(List<SelectorItem<String>> items) {
    SingleItemSelector<String, SelectorItem<String>> component = new SingleItemSelector<>(
        getTerminal(), items, "[Use enter to confirm], ", null);

    component.setResourceLoader(getResourceLoader());
    component.setTemplateExecutor(getTemplateExecutor());

    SingleItemSelectorContext<String, SelectorItem<String>> context = component
        .run(SingleItemSelectorContext.empty());

    return context.getResultItem().flatMap(si -> Optional.ofNullable(si.getItem())).get();
  }
}
