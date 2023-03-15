import styled from 'styled-components';

const Field = styled.div`
  label {
    display: block;
    margin-bottom: .5rem;
  }

  input {
    display: block;
    padding: .5rem;
    width: 80%;
  }

  span {
    display: block;
    margin-top: .5rem;
    text-align: center;
    color: red;
  }
`;

export default function TextField({name, label, register, errors}) {
  const id = `input-${label}`;
  return (
    <Field>
      <label htmlFor={id}>
        {label}
      </label>
      <input
        id={id}
        {...register(name, { required: true })}
      />
      {errors[name] && <span>필수 입력 값 입니다!</span>}
    </Field>
  );
}
