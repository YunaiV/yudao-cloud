/**
 * ai 模块下，接入 LLM 大模型，支持聊天、绘图、音乐、写作、思维导图等功能。
 * 目前已接入各种模型，不限于：
 * 国内：通义千问、文心一言、讯飞星火、智谱 GLM、DeepSeek
 * 国外：OpenAI、Ollama、Midjourney、StableDiffusion、Suno
 *
 * 1. Controller URL：以 /ai/ 开头，避免和其它 Module 冲突
 * 2. DataObject 表名：以 ai_ 开头，方便在数据库中区分
 */
package cn.iocoder.yudao.module.ai;
