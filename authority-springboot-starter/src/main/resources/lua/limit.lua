local key = KEYS[1]
-- tonumber 把字符串转为数字
local count = tonumber(ARGV[1])
local time = tonumber(ARGV[2])
-- 执行具体的 redis 指令
local current = redis.call('get', key)
if current and tonumber(current) > count then
    return tonumber(current)
end
current = redis.call('incr', key)
if tonumber(current) == 1 then
    redis.call('expire', key, time)
end
return tonumber(current)