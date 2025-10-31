package fr.eni.ludoteque.rest;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class JsonApiResponse<T> {
    @NonNull
    public String code;
    @NonNull
    public String message;

    public T data;
}
