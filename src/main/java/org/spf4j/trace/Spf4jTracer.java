///*
// * Copyright 2017 Zoltan Farkas.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.spf4j.trace;
//
//import io.opentracing.ActiveSpan;
//import io.opentracing.Span;
//import io.opentracing.SpanContext;
//import io.opentracing.Tracer;
//import io.opentracing.propagation.Format;
//import java.util.concurrent.ConcurrentMap;
//
///**
// *
// * @author Zoltan Farkas
// */
//public class Spf4jTracer implements Tracer {
//
//  private final ConcurrentMap
//
//  @Override
//  public SpanBuilder buildSpan(String operationName) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
//
//  @Override
//  public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
//
//  @Override
//  public <C> SpanContext extract(Format<C> format, C carrier) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
//
//  @Override
//  public ActiveSpan activeSpan() {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
//
//  @Override
//  public ActiveSpan makeActive(Span span) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
//
//}
