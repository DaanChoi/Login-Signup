# Login-Signup
> 일반 로그인 및 회원가입 기능 구현
<br>
Jdbc Template을 사용하여 구현

## 📘 기능 로직
> 일반 회원가입
1. 이메일(아이디), 비밀번호, 닉네임을 입력받습니다.
2. 입력받은 개인정보들은 컨트롤러 단에서 **형식적 Validation**을 거칩니다.
3. 서비스 단에서 **의미적 Validation**을 거칩니다.
4. DB에 저장 후 생성된 회원의 PK를 반환해줍니다. (비밀번호는 **암호화**하여 저장)

> 일반 로그인
1. 이메일과 비밀번호를 입력받습니다.
2. 입력받은 개인정보들은 컨트롤러 단에서 **형식적 Validation**을 거칩니다.
3. 해당 이메일에 해당하는 회원의 개인정보를 찾습니다.
4. 입력받은 비밀번호와 회원의 비밀번호가 일치하는지 비교합니다. (회원의 비밀번호는 **복호화**하여 비교)
