package tvz.btot.zavrsni.web.converter.base;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface BaseConverter <S, F, D> {
    default F sourceToForm(final S source) { throw new UnsupportedOperationException(); }
    default D sourceToDto(final S source) { throw new UnsupportedOperationException(); }
    default S formToSource(final F form) { throw new UnsupportedOperationException(); }

    default List<F> sourceToFormList(final List<S> sources) { return mapList(sources, this::sourceToForm); }
    default List<D> sourceToDtoList(final List<S> sources) { return mapList(sources, this::sourceToDto); }
    default List<S> formToSourceList(final List<F> forms) { return mapList(forms, this::formToSource); }

    private static <K, O> List<K> mapList(final List<O> list, final Function<O, K> function) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
