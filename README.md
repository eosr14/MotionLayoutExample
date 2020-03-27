## MotionLayout 

### MotionLayout

- 기존의 애니메이션을 추가하는 방식과는 다르게 레이아웃 요소로 애니메이션을 배치할 수 있다.
    - Android 기존 애니메이션 추가 방식
        - [Animated Vector Drawable](https://developer.android.com/guide/topics/graphics/drawable-animation)
        - [Property Animation framework](https://developer.android.com/guide/topics/graphics/prop-animation)
        - [LayoutTransition animations](https://developer.android.com/reference/android/animation/LayoutTransition)
        - [Layout transitions with TransitionManager](https://developer.android.com/training/transitions/)
        - [CoordinatorLayout](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout)
- 종속성 추가

    ~~~
    dependencies {
        implementation 'androidx.constraintlayout:constraintlayout:2.0.0-beta4'
    }
    ~~~

- 필요 조건
    - ConstraintLayout 2.0.0 이상 (필수)
    - Android Studio 4.0 이상 (옵션)
        - ConstraintLayout → MotionLayout Convert 기능 제공

### ConstraintSets

- 레이아웃에 대한 모든 규칙을 캡슐화하여 뷰를 다시 만들지 않고도 레이아웃에 적용할 규칙 세트를 즉석에서 결정할 수 있음
- MotionScene
    - res/xml 에 xml파일로 MotionScene가 보관됨

- MotionScene 파일에는 애니메이션을 지정하는데 필요한 모든 것이 포함될 수 있다.
    - ConstraintSets
    - ConstraintSets 사이의 전환
    - 키 프레임 / 터치 처리 등

### 속성

1. `<MotionLayout>` 속성
    - `app:layoutDescription="@xml/scene"` : MotionLayout Sence XML 파일 설정
    - `app:applyMotionScene="boolean"` : MotionScene 을 적용할지 여부 (기본값은 true)
    - `app:showPaths="boolean"` : 점선 Path를 보여줄지 여부 (릴리즈 / QA 시에는 false로 체크해야 함)
    - `app:progress="float"` : Transition Progress 설정 (범위 0~1)
    - `app:currentState="reference"` : 특정 ConstraintSet으로 설정
    - `app:motionDebug` : Debug 모드에서 모션 활성화 설정
        - NO_DEBUG / SHOW_ALL / SHOW_PATH / SHOW_PROGRESS

2. `<Transition>` 속성
    - `motion:constraintSetStart` : 시작할 시점의 ConstraintSet / Layout XML 파일을 설정
    - `motion:constraintSetEnd` : 종료할 시점의 ConstraintSet / Layout XML 파일을 설정
    - `motion:motionInterpolator` : 전체 애니메이션의 보간 처리
        - linear / easeIn / easeOut / easeInOut / bounce
    - `motion:duration` : 애니메이션 진행시간 설정

        (<OnSwipe> 핸들러에선 동작하지 않고, <OnClick> 핸들러에서 설정했을 경우에만 동작함)

    - `jumpToStart` : End 상태에 도달했을 때 Start 상태로 한번에 이동
    - `jumpToEnd` : Start 상태일 때 End 상태로 한번에 이동
    - `animateToStart` : End 상태에 도달했을 때 Start 상태로 애니메이션 실행
    - `animateToEnd` : Start 상태일 떄 End 상태로 애니메이션 실행

    2.1 `<OnClick>` 속성

    - `motion:motionTarge`t : target 뷰 id 설정
    - `motion:clickAction` : 애니메이션의 실행 방향 설정
        - toogle / transitionToEnd / transitionToStart / jumpToEnd / jumpToStart

    2.2 `<OnSwipe>` 속성

    - `motion:touchAnchorId` : target 뷰 id 설정
    - `motion:touchRegionId` : 터치 범위를 제한할 뷰 id 설정
    - `motion:touchAnchorSide` : 움직일 뷰의 측면 설정
        - top / left / right / bottom
    - `motion:dragDirection` : 스와이프 할 방향 설정
        - dragUp / dragDown / dragLeft / dragRight
    - `motion:dragScale` : 스와이프 거리의 factor 설정
    - `motion:moveWhenScrollAtTop` : target 뷰가 Scroll이 가능한 뷰(ScrollView, RecyclerView 등) 일 때는 스크롤을 먼저 실행하고 스크롤이 최상단에 왔을 경우 움직이도록 한다.

    2.3 `<KeyPosition>` 속성

    - `motion:motionTarget` : 타겟 뷰 id 설정
    - `motion:framePosition` : 애니메이션의 시작/끝 사이의 지점을 설정 (0~100)
    - `motion:percentX / motion:percentY` : 좌표 상에서의 백분율 거리

        (KeyPostionType 타입에 따라 기준 적용)

    - `motion:keyPositionType` : x/y 값을 계산하는 방법을 지정
        - `parentRelative` : 타겟뷰의 부모뷰를 기준으로 위치 계산 (시작 0, 끝 1, 가운데는 0.5)
        - `deltaRelative` : 시작과 끝 상태의 x,y 좌표를 델타값을 기준으로 위치 계산
        - `pathRelative` : 시작과 끝의 path 길이를 기준으로 위치 계산

    - `motion:percentWidth` : 시작과 끝의 width 차이를 기준으로 한 백분율
    - `motion:percentHeight` : 시작과 끝의 height 차이를 기준으로 한 백분율
    - `motion:sizePercent` : 시작 사이즈의 기준이 되는 백분율

    2.4 `<KeyCycle>` 속성

    - `motion:motionTarget` : 타겟 뷰 id 설정
    - `motion:framePosition` : 애니메이션의 시작/끝 사이의 지점을 설정 (0~100)
    - `motion:waveShape` : 진동 웨이브 모양 설정
        - square / triangle / sawtooth / reverseSawtooth / cos / bounce
    - `motion:wavePeriod` : 웨이브 진동 횟수 설정
    - `motion:waveOffset` : 속성 Offset 설정
    - `<CustomAttribute>` : 리플렉션을 통한 타겟뷰의 커스텀 설정

    2.5 `<KeyAttribute>` 속성

    - `motion:motionTarget` : 타겟 뷰 id 설정
    - `motion:framePosition` : 애니메이션의 시작/끝 사이의 지점을 설정 (0~100)
    - motion:transitionEasing : 애니메이션 시작시의 곡선 설정
        - curve(1.0,0,0,1.0) / standard / accelerate / decelerate / linear
    - motion:transitionPathRotate : 타겟 뷰가 Path에 따라 회전되도록 설정
    - `<CustomAttribute>` : 리플렉션을 통한 타겟뷰의 커스텀 설정

3. `<Constraint>` 속성

### Step by Step (Example)

- Step 1 - 기본 애니메이션 만들기 ( [**Layout**](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step1.xml) / [**MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step1_scene.xml)** )
- Step 2 - Drag 이벤트를 통해 애니메이션 제어하기 ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step2.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step2_scene.xml)** )
- Step 3 - 애니메이션 경로 제어하기  ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step3.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step3_scene.xml)** )
- Step 4 - 애니메이션 복잡한 경로 제어하기  ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step4.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step4_scene.xml)** )
- Step 5 - 모션 실행중 속성 변경  ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step5.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step5_scene.xml)** )
- Step 6 - 사용자 정의 속성 변경  ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step6.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step6_scene.xml)** )
- Step 7 - 이벤트 및 복잡한 경로 제어 ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step7.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step7_scene.xml)** )
- Step 8 - 코드로 모션 실행(코디네이터 레이아웃 + 모션 레이아웃) ( **[Layout](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/layout/activity_step8.xml)** / **[MotionSence](https://github.com/eosr14/MotionLayoutExample/blob/master/app/src/main/res/xml/activity_step8_scene.xml)** )

### 참고

- [https://codelabs.developers.google.com/codelabs/motion-layout](https://codelabs.developers.google.com/codelabs/motion-layout)
- [https://developer.android.com/training/constraint-layout/motionlayout/ref](https://developer.android.com/training/constraint-layout/motionlayout/ref)
- [https://medium.com/google-developers/introduction-to-motionlayout-part-i-29208674b10d](https://medium.com/google-developers/introduction-to-motionlayout-part-i-29208674b10d)
- [https://blog.gangnamunni.com/2019/12/03/MotionLayout.html](https://blog.gangnamunni.com/2019/12/03/MotionLayout.html)
