package tvz.btot.zavrsni.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class AllowableUrlMethods {
    private String controllerContextUri;
    private String uri;
    private EnumSet<HttpMethod> methods;

    public AllowableUrlMethods(final String controllerContextUri) {
        this.methods = EnumSet.noneOf(HttpMethod.class);
        this.controllerContextUri = controllerContextUri;
    }

    public AllowableUrlMethods() {
        this.methods = EnumSet.noneOf(HttpMethod.class);
    }

    public void setMethods(final RequestMethod[] methods) {
        this.methods.addAll(Arrays
                .stream(Optional.ofNullable(methods).orElse(new RequestMethod[]{}))
                .collect(Collectors.toList())
                .stream()
                .map(requestMethod -> HttpMethod.valueOf(requestMethod.name()))
                .collect(Collectors.toList()));
    }

    public void setMethods(final List<HttpMethod> methods) {
        this.methods.addAll(methods);
    }

    public void addMethod(final RequestMethod method) {
        this.methods.add(HttpMethod.valueOf(method.name()));
    }

    public void addMethod(final HttpMethod method) {
        this.methods.add(method);
    }

    public String getFormattedUri() {
        return controllerContextUri + uri;
    }
}
