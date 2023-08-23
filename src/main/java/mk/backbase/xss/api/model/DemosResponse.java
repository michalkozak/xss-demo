package mk.backbase.xss.api.model;

import java.util.List;

public record DemosResponse(List<DemoResponse> demos, long hits) {

}
