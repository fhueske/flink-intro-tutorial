/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * PLEASE NOTE:
 * This file has been modified by Fabian Hueske to add support for different sinks.
 */

package flink.tutorial.fraud_detection;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.walkthrough.common.sink.AlertSerializationSchema;
import org.apache.flink.walkthrough.common.sink.AlertStdOutSink;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.source.TransactionSource;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * Skeleton code for the datastream walkthrough
 */
public class FraudDetectionJob {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<Transaction> transactions = env
			.addSource(new TransactionSource())
			.name("transactions");

		DataStream<Alert> alerts = transactions
			.keyBy(Transaction::getAccountId)
			.process(new FraudDetector())
			.name("fraud-detector");


		appendStdOutSink(alerts);
//		appendKafkaSink(alerts);

		env.execute("Fraud Detection");
	}

	/**
	 * Appends a sink that writes a DataStream of alerts to the standard out.
	 *
	 * @param alerts the DataStream of alerts.
	 */
	private static void appendStdOutSink(DataStream<Alert> alerts) {
		alerts
			.addSink(new AlertStdOutSink())
			.name("write-alerts");
	}

	/**
	 * Appends a sink that writes a DataStream of alerts to a Kafka topic "alerts".
	 *
	 * @param alerts the DataStream of alerts.
	 */
	private static void appendKafkaSink(DataStream<Alert> alerts) {

		Properties kafkaProps = new Properties();
		kafkaProps.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");

		alerts
			.addSink(new FlinkKafkaProducer<>(
					"alerts",
					new AlertSerializationSchema("alerts"),
					kafkaProps,
					FlinkKafkaProducer.Semantic.AT_LEAST_ONCE))
			.name("emit-alerts");
	}
}
