alter table book_issue
    rename column book_id to book_instance_id;
alter table book_issue
    drop constraint book_issue_book_id_fkey,
    add constraint book_issue_book_instance_id_fkey foreign key (book_instance_id)
        references book_instance (id),
    drop column due_date;
