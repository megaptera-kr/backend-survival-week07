-- 'post' 테이블에 샘플 데이터 삽입
INSERT INTO post (id, title, author, content) VALUES
                                                  ('TSID1', '첫 번째 글 제목', '저자1', '첫 번째 글 내용'),
                                                  ('TSID2', '두 번째 글 제목', '저자2', '두 번째 글 내용'),
                                                  ('TSID3', '세 번째 글 제목', '저자3', '세 번째 글 내용');

-- 'comment' 테이블에 샘플 데이터 삽입
INSERT INTO comment (id, author, content, post_id) VALUES
                                                       ('TSID4', '댓글 작성자1', '첫 번째 글에 대한 첫 번째 댓글', 'TSID1'),
                                                       ('TSID5', '댓글 작성자2', '첫 번째 글에 대한 두 번째 댓글', 'TSID1'),
                                                       ('TSID6', '댓글 작성자3', '두 번째 글에 대한 첫 번째 댓글', 'TSID2');
