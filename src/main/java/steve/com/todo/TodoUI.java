package steve.com.todo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import steve.com.todo.model.Todo;
import steve.com.todo.service.TodoService;

import java.time.LocalDate;
import java.util.Set;


@Route("todo/:name")
public class TodoUI extends VerticalLayout implements BeforeEnterObserver {

    String author;

    @Autowired
    private TodoService todoService;

    Grid<Todo>  view = new Grid<>();

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        H2 title = new H2("TodoList: " + author.toUpperCase());
        add(title);

        Button btnAdd = new Button("Add new Item");
        btnAdd.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_SMALL);
        btnAdd.addClickListener(buttonClickEvent -> {
            Dialog dialog = createDialog();
            dialog.open();
        });

        Button btnRemove = new Button("Remove selected items");
        btnRemove.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);
        btnRemove.addClickListener(buttonClickEvent -> {
          Set<Todo> listTobeRemove = view.getSelectedItems();
          this.todoService.removeTodo(listTobeRemove);
          this.refreshItems();
        });


        add(new HorizontalLayout(btnAdd, btnRemove));
        view = new Grid();
        view.setSelectionMode(Grid.SelectionMode.MULTI);
        view.addColumn(Todo::getTitle);
        view.addColumn(Todo::getBody);
        view.addColumn(Todo::getAuthor);
        view.addColumn(Todo::getCreateAt);
        this.refreshItems();
        add(view);
    }

    private Dialog createDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("New Todo");

        VerticalLayout dialogLayout = new VerticalLayout();
        TextField title = new TextField("Title");
        dialogLayout.add(title);

        Button btnSave = new Button("Add new Item");
        btnSave.addClickListener(buttonClickEvent -> {
            Todo todo = Todo.builder()
                    .title(title.getValue())
                    .body(title.getValue())
                    .author(author)
                    .createAt(LocalDate.now())
                    .build();
            todoService.addTodo(todo);
            dialog.close();
            refreshItems();
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> dialog.close());
        dialog.getFooter().add(btnSave);
        dialog.getFooter().add(btnCancel);
        dialog.add(dialogLayout);
        return  dialog;
    }

    private void refreshItems(){
        view.setItems(todoService.getAllTodoItems());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
     this.author = event.getRouteParameters().get("name").get();
    }
}
