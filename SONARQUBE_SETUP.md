# SonarQube/SonarCloud セットアップ手順

## 1. SonarCloudアカウント作成
1. https://sonarcloud.io にアクセス
2. GitHubアカウントでログイン
3. Organization作成（18uma）

## 2. プロジェクト設定
1. SonarCloudで「+」→「Analyze new project」
2. `constant-batch-sample` を選択
3. 「Set Up」をクリック

## 3. GitHub Secretsの設定
1. GitHubリポジトリの Settings → Secrets and variables → Actions
2. 「New repository secret」をクリック
3. Name: `SONAR_TOKEN`
4. Value: SonarCloudで生成されたトークンを貼り付け

## 4. sonar-project.properties の更新
`sonar-project.properties` の以下を実際の値に更新：
- `sonar.projectKey`: SonarCloudで生成されたプロジェクトキー
- `sonar.organization`: あなたのOrganization名

## 5. build.gradle の更新
`build.gradle` の sonar セクションを更新：
```gradle
sonar {
    properties {
        property 'sonar.projectKey', 'your-actual-project-key'
        property 'sonar.organization', 'your-actual-org'
    }
}
```

## ローカルでの実行
```bash
./gradlew test jacocoTestReport sonar -Dsonar.token=YOUR_TOKEN
```

## CI/CDでの自動実行
GitHub Actionsが自動的にSonarCloud分析を実行します。
