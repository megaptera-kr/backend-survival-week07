import { useForm } from 'react-hook-form';

import styled from 'styled-components';

import Comment from './Comment';

import useFetchComments from './hooks/useFetchComments';
import useComment from './hooks/useComment';
import TextField from './TextField';

const Container = styled.div`
  & > form {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 10rem;
    padding: 1rem;
    border: 1px solid black;
  }
  
  button {
    padding: .3rem .5rem;
  }
  
  ul {
    padding: 0;
    list-style: none;
  }
`;

const Fields = styled.div`
  width: 70%;

  div:first-of-type {
    margin-bottom: 1rem;
  }
`;

const Submit = styled.div`
  margin: 0 auto;

  button {
    padding: .8rem 2rem;
  }
`;
export default function Comments({ postId }) {
  const { comments, reload } = useFetchComments({ postId });

  const { saveComment, deleteComment } = useComment({ postId });

  const { setValue, register, handleSubmit, formState: { errors } } = useForm();

  const handleSaveComment = async (data) => {
    await saveComment(data);

    setValue('author', '');
    setValue('content', '');

    reload();
  };

  const handleDeleteComment = async (id) => {
    await deleteComment(id);

    reload();
  };

  if (!comments) {
    return (
      <p>
        Now loading...
      </p>
    );
  }

  return (
    <Container>
      <form onSubmit={handleSubmit(handleSaveComment)}>
        <Fields>
          <TextField
            name="author"
            label="댓글 작성자"
            register={register}
            errors={errors}
          />
          <TextField
            name="content"
            label="댓글"
            register={register}
            errors={errors}
          />
        </Fields>
        <Submit>
          <button type="submit">
            저장하기
          </button>
        </Submit>
      </form>
      {!comments.length ? (
        <p>댓글이 없습니다ㅜㅠ</p>
      ) : (
        <ul>
          {comments.map((comment) => {
            return (
              <li key={comment.id}>
                <Comment
                  comment={comment}
                  onClickEdit={handleSaveComment}
                  onClickDelete={handleDeleteComment}
                />
              </li>
            );
          })}
        </ul>
      )}
    </Container>
  );
}
