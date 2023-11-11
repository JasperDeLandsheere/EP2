import java.util.Scanner;
// A factory that creates an 'SafeCreateOperation' object.
//
public class SafeCreateFactory implements SafeFactory // TODO: activate clause.
{
    @Override
    public SafeOperation create(Scanner sc) throws FactoryException {
        try {
            int width = sc.nextInt();
            int height = sc.nextInt();
            return new SafeCreateOperation(width, height);
        } catch (Exception e) {
            throw new FactoryException("Invalid input format");
        }
    }
}
