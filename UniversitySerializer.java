import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UniversitySerializer extends University {

    // Constructor: passes the university name up to the superclass (University)
    public UniversitySerializer(String name) {
        super(name);
    }

    // Writes all student data in JSON format into the specified file
    public void exportJSON(String filename) {

        try {
            // FileWriter is used to manually build and write JSON text
            FileWriter writer = new FileWriter(filename);

            // Start the JSON array
            writer.write("[\n");

            // Get the list of all students inherited from University
            ArrayList<Student> students = getStudents();

            // Loop through each student
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);

                // Start student object
                writer.write("  {\n");

                // Write basic student fields that every student has
                writer.write("    \"id\": \"" + s.getId() + "\",\n");
                writer.write("    \"name\": \"" + s.getName() + "\",\n");
                writer.write("    \"status\": \"" + s.getStatus() + "\",\n");

                // Tuition must be formatted to 2 decimal places
                writer.write("    \"tuitionDue\": \"" + String.format("%.2f", s.getTuitionDue()) + "\",\n");

                // Graduate-only field
                if (s instanceof GraduateStudent) {
                    GraduateStudent g = (GraduateStudent) s;
                    writer.write("    \"thesisTopic\": \"" + g.getThesisTopic() + "\",\n");
                }

                // Undergraduate-only field
                else if (s instanceof UndergraduateStudent) {
                    UndergraduateStudent u = (UndergraduateStudent) s;
                    writer.write("    \"yearLevel\": " + u.getYearLevel() + ",\n");
                }

                // Fields shared by all students
                writer.write("    \"creditHours\": " + s.getCreditHours() + ",\n");
                writer.write("    \"enrolledCoursesCount\": " + s.getEnrolledCoursesCount() + ",\n");

                // Handle enrolledCourses:
                // If a student has no courses, JSON must store "null"
                if (s.getEnrolledCoursesCount() == 0) {
                    writer.write("    \"enrolledCourses\": null\n");
                }

                // Otherwise, write nested course objects inside an array
                else {
                    writer.write("    \"enrolledCourses\": [\n");

                    // Retrieve the actual course objects
                    ArrayList<Course> courses = s.getEnrolledCourses();

                    // Loop through courses
                    for (int j = 0; j < courses.size(); j++) {
                        Course c = courses.get(j);

                        // Course object
                        writer.write("      {\n");
                        writer.write("        \"code\": \"" + c.getCode() + "\",\n");
                        writer.write("        \"title\": \"" + c.getTitle() + "\",\n");
                        writer.write("        \"creditHours\": " + c.getCreditHours() + "\n");
                        writer.write("      }");

                        // Add comma if not the last course in the array
                        if (j < courses.size() - 1) {
                            writer.write(",");
                        }
                        writer.write("\n");
                    }

                    writer.write("    ]\n");
                }

                // Close student object
                writer.write("  }");

                // Add comma if not the last student in the list
                if (i < students.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            // Close the JSON array
            writer.write("]\n");

            // Close the file
            writer.close();

        } catch (IOException e) {
            // If file writing fails, print required message
            System.out.println("Cannot Write File");
        }
    }
}