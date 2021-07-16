package adapters;

import objects.Project;

public class ProjectsAdapter extends BaseAdapter{

    private static final String PROJECT_URL = "/project";
    public static final String PROJECT = PROJECT_URL + "/%s";

    public void create(Project project) {
        post(PROJECT_URL, converter.toJson(project));
    }
}
