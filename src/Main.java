import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-Create");
        System.out.println("2-Active Task List");
        System.out.println("3-Finished Task List");
        System.out.println("4-Update (by id)");
        System.out.println("5-Delete (by id)");
        System.out.println("6-Mark as done");
        System.out.print("Select the desired number: ");
        int number=scanner.nextInt();

        String size;
        switch (number) {

            case 1:
                Scanner scanner1=new Scanner(System.in);
                System.out.print("Enter title: ");
                String title=scanner1.nextLine();
                Scanner scanner2=new Scanner(System.in);
                System.out.print("Enter content: ");
                String content=scanner2.nextLine();
                TaskService taskService=new TaskService();
                taskService.create(new TaskDTO(title,content));
                break;

            case 42:
                size = "Medium";
                break;

            // match the value of week
            case 44:
                size = "Large";
                break;

            case 48:
                size = "Extra Large";
                break;

            default:
                size = "Unknown";
                break;

        }

    }
}