import styled from 'styled-components';

import Comments from './Comments';

const Menu = styled.div`
  display: flex;
  justify-content: end;
  
  button {
    padding: .3rem .5rem;
    margin-right: 1rem;
  }
`;

const Author = styled.div`
  font-size: 1.1rem;
  margin-bottom: 3rem;
`;

const Content = styled.div`
  p {
    font-size: 1.1rem;
    padding: 0;
  }
`;



export default function Viewer({ post, onClickEdit, onClickDelete }) {
  return (
    <>
      <Menu>
        <button
          type="button"
          onClick={onClickEdit}
        >
          글 수정
        </button>
        <button
          type="button"
          onClick={() => onClickDelete(post.id)}
        >
          글 삭제
        </button>
      </Menu>
      <h2>
        제목:
        {' '}
        {post.title}
      </h2>
      <Author>
        작성자: {post.author}
      </Author>
      <Content>
        <p>
          내용:
        </p>
        <p>
          {post.content}
        </p>
      </Content>
      <Comments postId={post.id} />
    </>
  );
}
