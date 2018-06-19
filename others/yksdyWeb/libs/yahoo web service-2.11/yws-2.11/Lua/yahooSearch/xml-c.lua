----
-- Classic Lua-only implementation to parse XML data
-- by Roberto Ierusalimschy
-- http://lua-users.org/wiki/LuaXml
---- 

function parseargs (s)
  local arg = {}
  string.gsub(s, "(%w+)=([\"'])(.-)%2", function (w, _, a) arg[w] = a end)
  return arg
end

function collect (s)
  local stack = {n=0}
  local top = {n=0}
  table.insert(stack, top)
  local ni,c,label,args, empty
  local i, j = 1, 1
  while 1 do
    ni,j,c,label,args, empty = string.find(s, "<(%/?)(%w+)(.-)(%/?)>", j)
    if not ni then break end
    local text = string.sub(s, i, ni-1)
    if not string.find(text, "^%s*$") then
      table.insert(top, text)
    end
    if empty == "/" then  -- empty element tag
      table.insert(top, {n=0, label=label, args=parseargs(args), empty=1})
    elseif c == "" then   -- start tag
      top = {n=0, label=label, args=parseargs(args)}
      table.insert(stack, top)   -- new level
    else  -- end tag
      local toclose = table.remove(stack)  -- remove top
      top = stack[stack.n]
      if stack.n < 1 then
        error("nothing to close with "..label)
      end
      if toclose.label ~= label then
        error("trying to close "..toclose.label.." with "..label)
      end
      table.insert(top, toclose)
    end 
    i = j+1
  end
  local text = string.sub(s, i)
  if not string.find(text, "^%s*$") then
    table.insert(stack[stack.n], text)
  end
  if stack.n > 1 then
    error("unclosed "..stack[stack.n].label)
  end
  return stack[1]
end


-- example

x = collect[[
     <methodCall kind="xuxu">
      <methodName>examples.getStateName</methodName>
      <params>
         <param>
            <value><i4>41</i4></value>
            </param>
         </params>
      </methodCall>
]]
--print(x[1]['args']['kind'])