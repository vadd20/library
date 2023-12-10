alter table book
    drop column is_available,
    add column is_deleted BOOLEAN;