# 定常バッチ共通部品

Java + Spring 4.x + Gradle 9を使用した定常バッチのサンプル実装

[![CI](https://github.com/18uma/constant-batch-sample/actions/workflows/ci.yml/badge.svg)](https://github.com/18uma/constant-batch-sample/actions/workflows/ci.yml)

## 構成

### Core系（業務ロジック層）
- **BatchExecutor**: バッチ実行の基底クラス
- **BatchContext**: バッチ実行コンテキスト
- **BatchResult**: バッチ実行結果
- **TransactionManager**: トランザクション制御インターフェース

### Util系（汎用機能層）
- **ConfigLoader**: YAML設定ファイル読み込み
- **DateTimeUtil**: 日付・時刻操作
- **FileUtil**: ファイル操作（CSV対応）
- **LockManager**: 多重起動防止

## ビルド

```bash
./gradlew build
```

## 実行

```bash
./gradlew run --args="input.csv output.csv"
```

## サンプルバッチ

CsvProcessBatch: CSVファイルを読み込み、各レコードに処理日時を追加して出力

## テスト用CSVファイル作成

```bash
echo "id,name,value" > input.csv
echo "1,test1,100" >> input.csv
echo "2,test2,200" >> input.csv
```
