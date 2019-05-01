package ua.donskykh.mybookstore.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.domain.Book;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Book book = (Book) obj;
        if (book.getFile() == null || book.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Please select an image file to upload!");
            return;
        }
        if (!(book.getFile().getContentType().equals("image/jpeg")
                || book.getFile().getContentType().equals("image/png"))) {
            errors.rejectValue("file", null, "Please use only an image file to upload!");
            return;
        }
    }
}
