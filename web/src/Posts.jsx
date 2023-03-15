import _ from 'lodash';

import styled from 'styled-components';

const Container = styled.div`
  width: 40%;
  min-height: 100vh;

  p {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 100%;
    margin: 0;
    border-right: 1px solid black;
    text-align: center;
  }
  
  ul {
    margin: 0;
    padding: 0;
    min-height: 100vh;
    border-right: 1px solid black;
    list-style: none;
  }
`;

const Wrapper = styled.button`
  display: flex;
  padding-block: 1em;
  width: 100%;
  border: 0;
  background: transparent;
  cursor: pointer;
  :hover {
    background: cornflowerblue;
  }

  div {
    width: 20%;
    text-align: center;
  }

  div + div {
    padding-left: 1em;
    width: 50%;
    border-left: 1px solid black;
    text-align: start;
  }
  
  div + div + div {
    width: 30%;
  }
`;

const ListHead = styled.div`
  display: flex;
  padding-block: 1em;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
  text-align: center;
  
  div {
    width: 20%;
  }
  
  div + div {
    width: 50%;
  }
  
  div + div + div{
    width: 30%;
  }
`;

export default function Posts({ posts, onClick }) {
  if (!posts) {
    return (
      <Container>
        <p>loading...</p>
      </Container>
    );
  }

  if (_.isEmpty(posts)) {
    return (
      <Container>
        <p>게시물이 없습니다</p>
      </Container>
    );
  }

  return (
    <Container>
      <ListHead>
        <div>
          번호
        </div>
        <div>
          제목
        </div>
        <div>
          글쓴이
        </div>
      </ListHead>
      <ul>
        {posts.map((post, index) => {
          const { id, title, author } = post;

          return (
            <li key={id}>
              <Wrapper
                type="button"
                onClick={() => onClick(id)}
              >
                <div>
                  {index + 1}
                </div>
                <div>
                  {title}
                </div>
                <div>
                  {author}
                </div>
              </Wrapper>
            </li>
          );
        })}
      </ul>
    </Container>
  );
}
