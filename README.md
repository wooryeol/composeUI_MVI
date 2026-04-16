# 📱 WMS Android 프로젝트 기술 선택 이유

## 🧱 프로젝트 구조

```
com.example.app 
┣ 📂 core 
┃ ┗ 📂 base 
┃ ┣ MviContract.kt 
┃ ┗ MviViewModel.kt 
┣ 📂 data 
┃ ┗ 📂 catfact 
┃ ┣ remote 
┃ ┣ repository 
┃ ┗ mapper 
┣ 📂 presentation 
┃ ┣ navigation 
┃ ┗ catfact 
┃ ┣ CatFactContract.kt 
┃ ┣ CatFactViewModel.kt 
┃ ┣ CatFactScreen.kt 
┃ ┗ CatFactNavigation.kt 
┣ 📂 di 
┗ MainActivity.kt
```

---

# 🧠 기술 스택 선택 이유

## 1. Jetpack Compose

* 선언형 UI 기반으로 상태(State)에 따라 UI 자동 갱신
* 재고/입출고 등 **빈번한 상태 변화에 적합**
* XML 대비 코드 간결 → 유지보수 용이

---

## 2. MVI 아키텍처

```
Intent → ViewModel → State → UI
```

* 단방향 데이터 흐름으로 **예측 가능한 상태 관리**
* 하나의 State로 관리 → 복잡도 감소
* Effect 분리 → Toast / Navigation 안정적 처리
* 테스트 용이

---

## 3. Compose + MVI 조합

* 상태 기반 UI + 단일 상태 관리
* UI와 로직 흐름이 자연스럽게 연결
* 안정성과 유지보수성 확보

---

## 4. Feature 기반 구조

```
presentation/catfact
data/catfact
```

* 기능 단위로 코드 응집
* 확장 및 협업에 유리

---

# 📡 오프라인 대응 전략

## 🧠 핵심 개념

> **Local First + Sync 방식**

```
UI → Local DB → Sync Queue → WorkManager → Server
```

---

## ⚙️ 구성 요소

### 1. Room (SQLite)

* 데이터 로컬 저장
* UI는 항상 DB 기준으로 동작

### 2. Sync Queue

* 오프라인 작업 저장
* 네트워크 복구 시 서버 전송

### 3. WorkManager

* 백그라운드 동기화
* 네트워크 연결 시 자동 실행

---

## 🔄 동작 흐름

### 오프라인

* DB 저장 → Queue 적재 → UI 즉시 반영

### 온라인

* Queue → 서버 전송 → 성공 시 제거

---

## 🎯 기대 효과

* 오프라인에서도 정상 동작
* 데이터 유실 방지
* 빠른 사용자 응답
* 자동 동기화 지원

---

# 🧾 결론

* **Compose + MVI**로 안정적인 상태 관리
* **Offline First 구조**로 네트워크 의존성 최소화

👉 WMS 환경에 최적화된 구조 설계
