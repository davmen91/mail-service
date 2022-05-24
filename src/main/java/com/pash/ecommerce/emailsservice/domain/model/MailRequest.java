package com.pash.ecommerce.emailsservice.domain.model;

import com.pash.ecommerce.emailsservice.domain.util.MapperObjects;
import lombok.*;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class MailRequest implements Serializable  {

    private FilePart file;
    private DataRequest data;
    private static final long serialVersionUID = -2190761232268405198L;

    public MailRequest(FilePart file, FormFieldPart form) {
        this.file = file;
        this.data = MapperObjects._mapper(DataRequest.class, form.value());
    }

    public void setData(FormFieldPart form) {
        this.data = MapperObjects._mapper(DataRequest.class, form.value());
    }
}
